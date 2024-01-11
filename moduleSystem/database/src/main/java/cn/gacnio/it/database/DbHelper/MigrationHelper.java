package cn.gacnio.it.database.DbHelper;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;


import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.internal.DaoConfig;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * GreenDao数据库升级帮助类，
 */
public final class MigrationHelper {

    public static boolean DEBUG = false;
    private static String TAG = "xyz-MigrationHelper";
    private static final String SQLITE_MASTER = "sqlite_master";
    private static final String SQLITE_TEMP_MASTER = "sqlite_temp_master";

    private static WeakReference<ReCreateAllTableListener> weakListener;

    public interface ReCreateAllTableListener {
        void onCreateAllTables(Database db, boolean ifNotExists);

        void onDropAllTables(Database db, boolean ifExists);
    }

    /**
     *
     * @param db
     * @param daoClasses 不定项可变参数
     */
    public static void migrate(SQLiteDatabase db, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        printLog("【The Old Database Version】" + db.getVersion());
        Database database = new StandardDatabase(db);
        migrate(database, daoClasses);
    }

    public static void migrate(SQLiteDatabase db, ReCreateAllTableListener listener, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        weakListener = new WeakReference<>(listener);
        migrate(db, daoClasses);
    }

    public static void migrate(Database database, ReCreateAllTableListener listener, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        weakListener = new WeakReference<>(listener);
        migrate(database, daoClasses);
    }

    public static void migrate(Database database, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        printLog("【Generate temp table】start");
        generateTempTables(database, daoClasses);
        printLog("【Generate temp table】complete");

        ReCreateAllTableListener listener = null;
        if (weakListener != null) {
            listener = weakListener.get();
        }

        if (listener != null) {
            listener.onDropAllTables(database, true);
            printLog("【Drop all table by listener】");
            listener.onCreateAllTables(database, false);
            printLog("【Create all table by listener】");
        } else {
            //删除daoClasses对应的旧表
            dropAllTables(database, true, daoClasses);
            //创建daoClasses对应的新表
            createAllTables(database, false, daoClasses);
        }
        printLog("【Restore data】start");
        //从临时表挑选新表需要的数据
        //xyz 1.1 升级到 1.1.5版本不需要拷贝旧数据
//        restoreData(database, daoClasses);
//        printLog("【Restore data】complete");
    }

    /**
     * 复制临时表
     * @param db
     * @param daoClasses
     */
    private static void generateTempTables(Database db, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        for (int i = 0; i < daoClasses.length; i++) {
            String tempTableName = null;

            //创建DaoConfig 解析出tablename
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);
            String tableName = daoConfig.tablename;
            if (!isTableExists(db, false, tableName)) {
                printLog("【New Table】" + tableName);
                continue;
            }
            try {
                //临时表，如果存在先删除
                tempTableName = daoConfig.tablename.concat("_TEMP");
                StringBuilder dropTableStringBuilder = new StringBuilder();
                dropTableStringBuilder.append("DROP TABLE IF EXISTS ").append(tempTableName).append(";");
                db.execSQL(dropTableStringBuilder.toString());

                //注意这里： CREATE TEMPORARY TABLE
                //临时表只在当前连接可见，当这个连接关闭的时候，会自动drop。
                // 可以在两个不同的连接里使用相同的临时表名，并且相互不会冲突
                StringBuilder insertTableStringBuilder = new StringBuilder();
                insertTableStringBuilder.append("CREATE TEMPORARY TABLE ").append(tempTableName);
                //AS SELECT * FROM  从tableName挑选所有项出来构建tempTableName，相当于复制
                insertTableStringBuilder.append(" AS SELECT * FROM ").append(tableName).append(";");
                db.execSQL(insertTableStringBuilder.toString());
                printLog("【Table】" + tableName + "\n ---Columns-->" + getColumnsStr(daoConfig));
                printLog("【Generate temp table】" + tempTableName);
            } catch (SQLException e) {
                Log.e(TAG, "【Failed to generate temp table】" + tempTableName, e);
            }
        }
    }

    private static boolean isTableExists(Database db, boolean isTemp, String tableName) {
        if (db == null || TextUtils.isEmpty(tableName)) {
            return false;
        }
        String dbName = isTemp ? SQLITE_TEMP_MASTER : SQLITE_MASTER;
        String sql = "SELECT COUNT(*) FROM " + dbName + " WHERE type = ? AND name = ?";
        Cursor cursor = null;
        int count = 0;
        try {
            cursor = db.rawQuery(sql, new String[]{"table", tableName});
            if (cursor == null || !cursor.moveToFirst()) {
                return false;
            }
            count = cursor.getInt(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
        }
        return count > 0;
    }


    private static String getColumnsStr(DaoConfig daoConfig) {
        if (daoConfig == null) {
            return "no columns";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < daoConfig.allColumns.length; i++) {
            builder.append(daoConfig.allColumns[i]);
            builder.append(",");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }


    private static void dropAllTables(Database db, boolean ifExists, @NonNull Class<? extends AbstractDao<?, ?>>... daoClasses) {
        //反射执行每个dao类的dropTable方法，需要db和isExists两个参数
        reflectMethod(db, "dropTable", ifExists, daoClasses);
        printLog("【Drop all table by reflect】");
    }

    private static void createAllTables(Database db, boolean ifNotExists, @NonNull Class<? extends AbstractDao<?, ?>>... daoClasses) {
        //反射执行每个dao类的createTable方法，需要db和isExists两个参数
        reflectMethod(db, "createTable", ifNotExists, daoClasses);
        printLog("【Create all table by reflect】");
    }

    /**
     * dao class already define the sql exec method, so just invoke it
     */
    private static void reflectMethod(Database db, String methodName, boolean isExists, @NonNull Class<? extends AbstractDao<?, ?>>... daoClasses) {
        if (daoClasses.length < 1) {
            return;
        }
        try {
            for (Class cls : daoClasses) {
                Method method = cls.getDeclaredMethod(methodName, Database.class, boolean.class);
                method.invoke(null, db, isExists);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void restoreData(Database db, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        for (int i = 0; i < daoClasses.length; i++) {
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);
            String tableName = daoConfig.tablename;
            String tempTableName = daoConfig.tablename.concat("_TEMP");

            if (!isTableExists(db, true, tempTableName)) {
                continue;
            }

            try {
                // get all columns from tempTable, take careful to use the columns list
                List<String> columns = getColumns(db, tempTableName);
                ArrayList<String> properties = new ArrayList<>(columns.size());
                for (int j = 0; j < daoConfig.properties.length; j++) {
                    String columnName = daoConfig.properties[j].columnName;
//                    if (columns.contains(columnName)) {
//                        properties.add("`" + columnName + "`");
//                    }
                    /**
                     * ——————————————————————————————————————————————————————————
                     * 数据库升级方案，大概思路是：
                     * 1、新建一张跟原先表一样的临时表，同时把数据也复制进去。
                     * 2、删除原先表
                     * 3、新建最新的表，将临时表数据复制进去
                     * 4、删除临时表
                     * 问题就出在第三步，如果临时表有1个字段。新表有2个字段，
                     * 而且新多的字段为int类型，那么复制数据的时候，字段二（新增）是存NULL，
                     * 但是其属性是不能为NULL，这就异常了，
                     * 这里有个解决思路：在临时表上也给多加一个一样的字段，
                     * 然后给默认值，因为有了默认值，所有当复制数据的时候就不会是NULL
                     * ——————————————————————————————————————————————————————————
                     */
                    if (!columns.contains(columnName)) {
                        StringBuilder insertTableStringBuilder = new StringBuilder();
                        insertTableStringBuilder.append("ALTER TABLE "+tempTableName +" ADD COLUMN "+columnName+
                                getTableType(daoConfig.properties[j].type));
                        db.execSQL(insertTableStringBuilder.toString());
                    }
                    properties.add("`" + columnName + "`");
                }

                if (properties.size() > 0) {
                    final String columnSQL = TextUtils.join(",", properties);

                    StringBuilder insertTableStringBuilder = new StringBuilder();
                    insertTableStringBuilder.append("REPLACE INTO ").append(tableName).append(" (");
                    insertTableStringBuilder.append(columnSQL);
                    insertTableStringBuilder.append(") SELECT ");
                    insertTableStringBuilder.append(columnSQL);
                    insertTableStringBuilder.append(" FROM ").append(tempTableName).append(";");
                    db.execSQL(insertTableStringBuilder.toString());
                    printLog("【Restore data】 to " + tableName);
                }
                StringBuilder dropTableStringBuilder = new StringBuilder();
                dropTableStringBuilder.append("DROP TABLE ").append(tempTableName);
                db.execSQL(dropTableStringBuilder.toString());
                printLog("【Drop temp table】" + tempTableName);
            } catch (SQLException e) {
                Log.e(TAG, "【Failed to restore data from temp table 】" + tempTableName, e);
            }
        }
    }


    private static Object getTableType(Class<?> type){
        if(type.equals(int.class)){
            return " INTEGER DEFAULT 0";
        }
        if(type.equals(long.class)){
            return " Long DEFAULT 0";
        }
        if(type.equals(String.class)){
            return " TEXT ";
        }
        if(type.equals(boolean.class)){
            return " NUMERIC DEFAULT 0";
        }
        return " TEXT";
    }
    private static List<String> getColumns(Database db, String tableName) {
        List<String> columns = null;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + tableName + " limit 0", null);
            if (null != cursor && cursor.getColumnCount() > 0) {
                columns = Arrays.asList(cursor.getColumnNames());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            if (null == columns) columns = new ArrayList<>();
        }
        return columns;
    }

    private static void printLog(String info) {
        if (DEBUG) {
            Log.i(TAG, info);
        }
    }

}

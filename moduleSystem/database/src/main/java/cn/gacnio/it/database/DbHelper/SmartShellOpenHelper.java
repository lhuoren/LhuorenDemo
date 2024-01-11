package cn.gacnio.it.database.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import org.greenrobot.greendao.database.Database;

import cn.gacnio.it.database.greendao.DaoMaster;
import cn.gacnio.it.database.greendao.ModuleEntityDao;

public class SmartShellOpenHelper extends DaoMaster.OpenHelper {

    public SmartShellOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 数据库升级  只针对SmartShell base库操作
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //操作数据库的更新
        //依据每个版本的更新需求，只需要将需要升级的实体数据库进行如下操作：便可保留所有原有数据，
        Log.e("xyz greenDAO", "gacnio.db onUpgrade schema from version " + oldVersion + " to " + newVersion );
        if(newVersion > oldVersion) {
            //把需要管理的数据库表DAO作为最后一个参数传入到方法中
            MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

                @Override
                public void onCreateAllTables(Database db, boolean ifNotExists) {
                    DaoMaster.createAllTables(db, ifNotExists);
                }

                @Override
                public void onDropAllTables(Database db, boolean ifExists) {
                    DaoMaster.dropAllTables(db, ifExists);
                }
            }, ModuleEntityDao.class);

            //每次base库升级在此处做对应操作
            //最后检测未被创建的新表，如果存在不做处理，如果不存在，将被统一创建出来
//        DatabaseManager.createAllBaseTables(db);
        }

    }
}

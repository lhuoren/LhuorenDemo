package cn.gacnio.it.database.action;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import java.util.concurrent.atomic.AtomicReference;

import cn.gacnio.it.database.DbHelper.SmartShellOpenHelper;
import cn.gacnio.it.database.DbHelper.UserOpenHelper;
import cn.gacnio.it.database.SPUtils;
import cn.gacnio.it.database.greendao.DaoMaster;
import cn.gacnio.it.database.greendao.DaoSession;


/**
 * 数据库管理
 */
public class DatabaseManager {

    private static DaoMaster sBaseDaoMaster;
    private static DaoSession sBaseDaoSession;
    private static DaoMaster sUserDaoMaster;
    private static DaoSession sUserDaoSession;

    @SuppressLint("StaticFieldLeak")
    private static Context sAppContext;//make sure this's a AppContext
    private static final String USER_NAME = "Smartshell_USER_NAME";
    private static boolean isCloseUserDb=false;
    static final AtomicReference<DatabaseManager> INSTANCE = new AtomicReference<>(new DatabaseManager());

    public static DatabaseManager get() {
        DatabaseManager dbManager = INSTANCE.get();
        if (null == dbManager) {//正常情况没可能为空
            dbManager = new DatabaseManager();
            INSTANCE.set(dbManager);
        }
        if (null == sAppContext) {
            return dbManager;
        }
        if (null == dbManager.sUserDaoSession &&!isCloseUserDb) {
            String userName = SPUtils.getString(USER_NAME, "");
            if (!TextUtils.isEmpty(userName)) {
            }
        }
        if (null == dbManager.sBaseDaoSession) {
            dbManager.initBaseDB(sAppContext);
        }
        return dbManager;
    }

    // 基础库 +当前用户信息
    public void initBaseDB(Context context) {
        this.sAppContext = context;
        SmartShellOpenHelper devOpenHelper = new SmartShellOpenHelper(context, "gacnio.db",null);
        //获取可写数据库
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        if (sBaseDaoMaster == null) {
            sBaseDaoMaster = new DaoMaster(db);
        }
        sBaseDaoSession = sBaseDaoMaster.newSession();
//        sBaseDaoSession.clear();//清空所有数据表的缓存

    }

    // 用户行为库
    public void initUserDB(Context context, String userDbName) {
        isCloseUserDb=false;
        SPUtils.put(USER_NAME, userDbName);
        UserOpenHelper devOpenHelper = new UserOpenHelper(context, userDbName + "-db", null);
        //获取可写数据库
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        if (sUserDaoMaster == null) {
            sUserDaoMaster = new DaoMaster(db);
        }
        sUserDaoSession = sUserDaoMaster.newSession();

        Log.d("DatabaseManager", "userDaoSession--------:" + sUserDaoSession);
    }

    // 基础DaoSession
    public DaoSession getBaseDaoSession() {
        return sBaseDaoSession;
    }

    //清空当前数据库操作
    public void clearUserDaoSession(){
        isCloseUserDb=true;
        if(sUserDaoSession !=null) sUserDaoSession =null;
    }


    // 用户DaoSession
    public DaoSession getUserDaoSession() {
        return sUserDaoSession;
    }

    /**
     * 创建所有Base库 新建的表
     * 用于升级
     * @param db
     */
    public static synchronized void createAllBaseTables(Database db) {
        if(sBaseDaoMaster == null){
            sBaseDaoMaster = new DaoMaster(db);
        }
        if(sBaseDaoMaster != null){
            sBaseDaoMaster.createAllTables(sBaseDaoMaster.getDatabase(), true);
        }
    }

    /**
     * 创建所有User库 新建的表
     * 用于升级
     * @param db
     */
    public static synchronized void createAllUserTables(Database db) {
        if(sUserDaoMaster == null){
            sUserDaoMaster = new DaoMaster(db);
        }
        if(sUserDaoMaster != null){
            sUserDaoMaster.createAllTables(sUserDaoMaster.getDatabase(), true);
        }
    }
}

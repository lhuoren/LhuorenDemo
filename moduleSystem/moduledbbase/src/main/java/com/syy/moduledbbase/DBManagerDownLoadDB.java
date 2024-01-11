package com.syy.moduledbbase;

import android.content.Context;

import com.mapbox.android.core.BuildConfig;
import com.syy.moduledbbase.db.greedao.DaoMaster;
import com.syy.moduledbbase.db.greedao.DaoSession;
import com.syy.moduledbbase.db.utils.SharedPrefaceUtils;
import com.syy.moduledbbase.db.utils.SpConstants;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by smilelu on 2018/4/14.
 */
public class DBManagerDownLoadDB {

    public static String DBPATH = "";

    public static final boolean ENCRYPTED = true;

    private static final String DEFAULT_DB_NAME = "smart_patrol.db";
    //    private static final String DEFAULT_DB_NAME_ENCRYPTED = "smart_patrol_encrypted.db";
    private static final String DEFAULT_DB_NAME_ENCRYPTED = "smart_patrol_encrypted_" + SharedPrefaceUtils.getString(SpConstants.SharedName.UN) + ".db";

    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public static void init(Context context) {

        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }

        setQueryBuilderLog(BuildConfig.DEBUG);

    }

    public static DBManagerDownLoadDB getInstance() {
        return InstanceHolder.instance;
    }

    public static synchronized void reload() {
        if (DBManagerDownLoadDB.InstanceHolder.instance != null && DBManagerDownLoadDB.InstanceHolder.instance.daoSession != null) {
            DBManagerDownLoadDB.InstanceHolder.instance.daoSession.clear();
            DBManagerDownLoadDB.InstanceHolder.instance = null;
        }
        DBManagerDownLoadDB.InstanceHolder.instance = new DBManagerDownLoadDB();
    }

    private static class InstanceHolder {
        private static DBManagerDownLoadDB instance = new DBManagerDownLoadDB();
    }

    private DBManagerDownLoadDB() {
        SpSQLiteOpenHelper helper = new SpSQLiteOpenHelper(new DownLoadDBGreenDaoContext(), ENCRYPTED ? DEFAULT_DB_NAME_ENCRYPTED : DEFAULT_DB_NAME, null);
        try {
            Database database = ENCRYPTED ? helper.getEncryptedWritableDb("Abc123#456") : helper.getWritableDb();
            daoMaster = new DaoMaster(database);
            daoSession = daoMaster.newSession();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * 设置是否开启LOG
     *
     * @param enable
     */
    private static void setQueryBuilderLog(boolean enable) {
        QueryBuilder.LOG_SQL = enable;
        QueryBuilder.LOG_VALUES = enable;
    }
}


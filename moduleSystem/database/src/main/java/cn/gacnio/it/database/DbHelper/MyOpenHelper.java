package cn.gacnio.it.database.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import org.greenrobot.greendao.database.Database;

import cn.gacnio.it.database.greendao.DaoMaster;


public class MyOpenHelper extends DaoMaster.OpenHelper {

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //操作数据库的更新 有几个表升级都可以传入到下面
       // MigrationHelper.getInstance().migrate(db,CurrentUserDBDao.class);
        Log.i("greenDAO", "onUpgrade schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
    }


}
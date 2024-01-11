package cn.gacnio.it.database.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import org.greenrobot.greendao.database.Database;

import cn.gacnio.it.database.action.DatabaseManager;
import cn.gacnio.it.database.greendao.DaoMaster;

public class UserOpenHelper extends DaoMaster.OpenHelper {

    public UserOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 数据库升级  只针对SmartShell user库操作
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //操作数据库的更新
        //依据每个版本的更新需求，只需要将需要升级的实体数据库进行如下操作：便可保留原有数据，
        //传入的Dao类对应的表数据将被复制到新表，旧表将删除
        Log.i("greenDAO", "user.db  onUpgrade schema from version " + oldVersion + " to " + newVersion);
        /*if(newVersion >= 3) {   用法示例
            MigrationHelper.migrate(db, CurrentUserDBDao.class);
        }*/
        //最后检测未被创建的新表，如果存在不做处理，如果不存在，将被统一创建出来
        DatabaseManager.createAllUserTables(db);
    }
}

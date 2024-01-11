package com.syy.moduledbbase;

import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.syy.moduledbbase.db.utils.LogUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by smilelu on 2018/4/14.
 */
public class DownLoadDBGreenDaoContext extends ContextWrapper {

    public DownLoadDBGreenDaoContext() {
        super(BaseApplication.getInstanceContext());
    }

    /**
     * 获得数据库路径，如果不存在，则创建对象
     *
     * @param dbName
     */
    @Override
    public File getDatabasePath(String dbName) {
        String dbDir = DBManagerDownLoadDB.DBPATH;
        LogUtils.i("DBManager.DBPATH2", dbDir);
        if (TextUtils.isEmpty(dbDir)) {
            Log.e("SD卡管理：", "SD卡不存在，请加载SD卡");
            return null;
        }
        File baseFile = new File(dbDir);
        if (!baseFile.exists()) {
            baseFile.mkdirs();
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(baseFile.getPath());
        dbDir = buffer.toString();
        buffer.append(File.separator);
        buffer.append(dbName);
        String dbPath = buffer.toString();
        File dirFile = new File(dbDir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        boolean isFileCreateSuccess = false;

        File dbFile = new File(dbPath);
        if (!dbFile.exists()) {
            try {
                isFileCreateSuccess = dbFile.createNewFile();// 创建文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            isFileCreateSuccess = true;

        if (isFileCreateSuccess)
            return dbFile;
        else
            return super.getDatabasePath(dbName);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                                               SQLiteDatabase.CursorFactory factory) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }

}

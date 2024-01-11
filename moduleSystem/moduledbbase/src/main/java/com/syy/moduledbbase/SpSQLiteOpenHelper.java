package com.syy.moduledbbase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.syy.moduledbbase.db.greedao.ChannelAirTaskBeanDao;
import com.syy.moduledbbase.db.greedao.ChildRoutesDao;
import com.syy.moduledbbase.db.greedao.DaoMaster;
import com.syy.moduledbbase.db.greedao.FineAirTaskBeanDao;
import com.syy.moduledbbase.db.greedao.FineStepDao;
import com.syy.moduledbbase.db.greedao.FlyRecordDao;
import com.syy.moduledbbase.db.greedao.FlyRecordDetailDao;
import com.syy.moduledbbase.db.greedao.ImageInfoDao;
import com.syy.moduledbbase.db.greedao.PanoramaAirTaskBeanDao;
import com.syy.moduledbbase.db.greedao.PatrolTaskDao;
import com.syy.moduledbbase.db.greedao.PlanChannelAirTaskDao;
import com.syy.moduledbbase.db.greedao.PlanFineAirTaskDao;
import com.syy.moduledbbase.db.greedao.PlanTaskDao;
import com.syy.moduledbbase.db.greedao.PointCloudAirTaskBeanDao;
import com.syy.moduledbbase.db.greedao.TaskDataDao;
import com.syy.moduledbbase.db.greedao.TaskMarkerDao;
import com.syy.moduledbbase.db.greedao.TaskParamsDao;
import com.syy.moduledbbase.db.utils.LogUtils;

import org.greenrobot.greendao.database.Database;

/**
 * Created by l on 2018/5/11.
 */
public class SpSQLiteOpenHelper extends DaoMaster.OpenHelper {

    public SpSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, PatrolTaskDao.class, TaskDataDao.class,
                TaskMarkerDao.class, TaskParamsDao.class, FlyRecordDao.class,
                FlyRecordDetailDao.class, FineStepDao.class,
                ImageInfoDao.class, ChannelAirTaskBeanDao.class, FineAirTaskBeanDao.class,
                PanoramaAirTaskBeanDao.class, PointCloudAirTaskBeanDao.class,
                PlanTaskDao.class, ChildRoutesDao.class, PlanChannelAirTaskDao.class,
                PlanFineAirTaskDao.class);
        LogUtils.i("onUpgrade", "onUpgrade");
    }
}

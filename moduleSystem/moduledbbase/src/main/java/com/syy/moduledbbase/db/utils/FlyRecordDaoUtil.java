package com.syy.moduledbbase.db.utils;

import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.modulecommon.FlyRecord;
import com.syy.moduledbbase.db.greedao.FlyRecordDao;

import java.util.Date;
import java.util.List;


public class FlyRecordDaoUtil {

    private FlyRecordDao flyRecordDao;

    public FlyRecordDaoUtil() throws Exception {
        flyRecordDao = DBManager.getInstance().getDaoSession().getFlyRecordDao();
    }

    public FlyRecord getFlyRecord(long recordId) throws Exception {
        return flyRecordDao.load(recordId);
    }

    public FlyRecord getFlyRecordWithTaskId(String taskId) throws Exception {
        return flyRecordDao.queryBuilder().where(FlyRecordDao.Properties.AirLineTaskId.eq(taskId)).unique();
    }

    public List<FlyRecord> getFlyRecordListWithTaskId(String taskId) throws Exception {
        return flyRecordDao.queryBuilder().where(FlyRecordDao.Properties.AirLineTaskId.eq(taskId)).orderDesc(FlyRecordDao.Properties.CreateDate).list();
    }

    public FlyRecord getFlyRecord(long patrolTaskId, int slantingType) throws Exception {
        return flyRecordDao.queryBuilder().where(FlyRecordDao.Properties.TaskId.eq(patrolTaskId), FlyRecordDao.Properties.SlantingType.eq(slantingType)).unique();
    }

    public FlyRecord getFlyRecordByCreateDateAndIsImagesUpload(Date date, boolean isImagesUpload) throws Exception {
        return flyRecordDao.queryBuilder().where(FlyRecordDao.Properties.CreateDate.eq(date), FlyRecordDao.Properties.IsImagesUpload.eq(isImagesUpload)).orderDesc(FlyRecordDao.Properties.CreateDate).unique();
    }

    public List<FlyRecord> getFlyRecordListByIsImagesUpload( boolean isImagesUpload) throws Exception {
        return flyRecordDao.queryBuilder().where(FlyRecordDao.Properties.IsImagesUpload.eq(isImagesUpload)).orderDesc(FlyRecordDao.Properties.CreateDate).list();
    }

    public List<FlyRecord> getFlyRecordList(long patrolTaskId, int slantingType) throws Exception {
        return flyRecordDao.queryBuilder().where(FlyRecordDao.Properties.TaskId.eq(patrolTaskId), FlyRecordDao.Properties.SlantingType.eq(slantingType)).list();
    }

    public List<FlyRecord> getAllFlyRecord() throws Exception {
        return flyRecordDao.queryBuilder().orderDesc(FlyRecordDao.Properties.CreateDate).list();
    }

    public List<FlyRecord> updateAllFlyRecord() throws Exception {
        return flyRecordDao.queryBuilder().orderAsc(FlyRecordDao.Properties.CreateDate).list();
    }

    public List<FlyRecord> getAllFlyRecordUnUpload() throws Exception {
        return flyRecordDao.queryBuilder().where(FlyRecordDao.Properties.IsUpload.eq(false), FlyRecordDao.Properties.IsSimulation.eq(0)).orderAsc(FlyRecordDao.Properties.CreateDate).list();
    }

    public List<FlyRecord> getAllFlyRecordUnUploadByDate(Date date) throws Exception {
        return flyRecordDao.queryBuilder().where(FlyRecordDao.Properties.EndTime.isNotNull(), FlyRecordDao.Properties.IsSimulation.eq(0), FlyRecordDao.Properties.CreateDate.gt(date)).orderAsc(FlyRecordDao.Properties.CreateDate).list();
    }

    public Long insert(FlyRecord flyRecord) throws Exception {
        return flyRecordDao.insert(flyRecord);
    }

    public void update(FlyRecord flyRecord) throws Exception {
        flyRecordDao.update(flyRecord);
    }

    public void delete(FlyRecord flyRecord) throws Exception {
        flyRecordDao.delete(flyRecord);
    }

    public FlyRecordDao getFlyRecordDao() {
        return flyRecordDao;
    }

    public void setFlyRecordDao(FlyRecordDao flyRecordDao) {
        this.flyRecordDao = flyRecordDao;
    }
}

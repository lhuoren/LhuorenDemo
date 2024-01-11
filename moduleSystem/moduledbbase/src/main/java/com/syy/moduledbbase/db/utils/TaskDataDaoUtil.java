package com.syy.moduledbbase.db.utils;

import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.modulecommon.FlyRecord;
import com.syy.moduledbbase.db.entity.modulecommon.TaskData;
import com.syy.moduledbbase.db.greedao.TaskDataDao;

import java.util.List;

public class TaskDataDaoUtil {

    private TaskDataDao taskDataDao;

    public TaskDataDaoUtil() throws Exception {
        taskDataDao = DBManager.getInstance().getDaoSession().getTaskDataDao();
    }

    public Long insert(TaskData taskData) throws Exception {
        return taskDataDao.insert(taskData);
    }

    public Long insertOrReplace(TaskData taskData) throws Exception {
        return taskDataDao.insertOrReplace(taskData);
    }

    public void delete(long patrolTaskId, int slantingType) throws Exception {
        FlyRecord flyRecord = new FlyRecordDaoUtil().getFlyRecord(patrolTaskId, slantingType);
        if (flyRecord != null) {
            taskDataDao.queryBuilder().where(TaskDataDao.Properties.RecordId.eq(flyRecord.getId())).buildDelete().executeDeleteWithoutDetachingEntities();
        }
    }

    public void deleteList(long patrolTaskId, int slantingType) throws Exception {
        List<FlyRecord> flyRecords = new FlyRecordDaoUtil().getFlyRecordList(patrolTaskId, slantingType);
        for (FlyRecord flyRecord : flyRecords) {
            if (flyRecord != null) {
                taskDataDao.queryBuilder().where(TaskDataDao.Properties.RecordId.eq(flyRecord.getId())).buildDelete().executeDeleteWithoutDetachingEntities();
            }
        }
    }

    public List<TaskData> getTaskDataByRecord(Long recordId) throws Exception {
        return taskDataDao.queryBuilder().where(TaskDataDao.Properties.RecordId.eq(recordId)).list();
    }

    public List<TaskData> getTaskDataByFlyWorkId(String flyWorkId) throws Exception {
        return taskDataDao.queryBuilder().where(TaskDataDao.Properties.FlyTaskWorkId.eq(flyWorkId)).list();
    }

    public List<TaskData> getTaskDataByFlyWorkIdAndCruiseId(String flyWorkId, String cruiseId) throws Exception {
        return taskDataDao.queryBuilder().where(TaskDataDao.Properties.FlyTaskWorkId.eq(flyWorkId), TaskDataDao.Properties.FlyTaskWorkCruiseId.eq(cruiseId)).list();
    }

    public List<TaskData> getTaskDataAll() throws Exception {
        return taskDataDao.queryBuilder().list();
    }

    public TaskDataDao getTaskDataDao() {
        return taskDataDao;
    }

    public void setTaskDataDao(TaskDataDao taskDataDao) {
        this.taskDataDao = taskDataDao;
    }
}

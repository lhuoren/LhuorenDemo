package com.syy.moduledbbase.db.utils;

import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.modulecommon.PatrolTask;
import com.syy.moduledbbase.db.greedao.PatrolTaskDao;
import java.util.List;

/**
 * Created by smilelu on 2018/4/16.
 */
public class PatrolTaskDaoUtil {

    private PatrolTaskDao patrolTaskDao;

    public PatrolTaskDaoUtil() throws Exception {
        patrolTaskDao = DBManager.getInstance().getDaoSession().getPatrolTaskDao();
    }

    public Long insert(PatrolTask task) throws Exception {
        return patrolTaskDao.insert(task);
    }

    public void update(PatrolTask task) throws Exception {
        patrolTaskDao.update(task);
    }

    public Long insertOrReplace(PatrolTask task) throws Exception {
        return patrolTaskDao.insertOrReplace(task);
    }

    public PatrolTask getPatrolTaskById(Long taskId) throws Exception {
        return patrolTaskDao.load(taskId);
    }

    public List<PatrolTask> getPatrolTasks(int taskType) throws Exception {
        if (taskType == 0) {
            return patrolTaskDao.queryBuilder().orderDesc(PatrolTaskDao.Properties.CreateDate).list();
        }
        return patrolTaskDao.queryBuilder().where(PatrolTaskDao.Properties.TaskType.eq(taskType)).orderDesc(PatrolTaskDao.Properties.CreateDate).list();
    }

    public List<PatrolTask> getPatrolTasksById(long id) throws Exception {
        return patrolTaskDao.queryBuilder().where(PatrolTaskDao.Properties.Id.eq(id)).list();
    }

    public List<PatrolTask> getPatrolTasks(int taskType, int page) throws Exception {
        if (taskType == 0) {
            return patrolTaskDao.queryBuilder().offset((page - 1) * SpConstants.PER_PAGE_NUMBER).limit(SpConstants.PER_PAGE_NUMBER).orderDesc(PatrolTaskDao.Properties.CreateDate).list();
        }
        return patrolTaskDao.queryBuilder().where(PatrolTaskDao.Properties.TaskType.eq(taskType)).offset((page - 1) * SpConstants.PER_PAGE_NUMBER).limit(SpConstants.PER_PAGE_NUMBER).orderDesc(PatrolTaskDao.Properties.CreateDate).list();
    }

    public void delete(PatrolTask task) throws Exception {
        patrolTaskDao.delete(task);
    }
}

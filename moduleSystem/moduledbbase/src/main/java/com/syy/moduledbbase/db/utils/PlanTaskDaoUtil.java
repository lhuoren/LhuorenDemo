package com.syy.moduledbbase.db.utils;

import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.moduleuav.plantask.PlanTask;
import com.syy.moduledbbase.db.entity.moduleuav.plantask.line.ChildRoutes;
import com.syy.moduledbbase.db.greedao.ChildRoutesDao;
import com.syy.moduledbbase.db.greedao.PlanTaskDao;

import java.util.List;

public class PlanTaskDaoUtil {

    private final PlanTaskDao planTaskDao;

    public PlanTaskDaoUtil() throws Exception {
        planTaskDao = DBManager.getInstance().getDaoSession().getPlanTaskDao();
    }

    public Long insert(PlanTask planTask) throws Exception {
        return planTaskDao.insert(planTask);
    }

    public void update(PlanTask planTask) throws Exception {
        planTaskDao.update(planTask);
    }

    public Long insertOrReplace(PlanTask planTask) throws Exception {
        return planTaskDao.insertOrReplace(planTask);
    }

    public List<PlanTask> getPlanTaskList() throws Exception {
        return planTaskDao.queryBuilder().list();
    }

    public List<PlanTask> getPlanTaskListWithTaskstate(int taskstate) throws Exception {
        return planTaskDao.queryBuilder().where(PlanTaskDao.Properties.Taskstate.eq(taskstate)).list();
    }

    public List<PlanTask> getPlanTaskListWithTaskStateAndPageIdTenRec(int taskstate, int offset) throws Exception {
        return planTaskDao.queryBuilder()
                .where(PlanTaskDao.Properties.Taskstate.eq(taskstate))
                .offset(offset * 3)
                .limit(3)
                .list();
    }

    public PlanTask getPlanTaskWithTaskId(String airLineTaskId) throws Exception {
        return planTaskDao.queryBuilder().where(PlanTaskDao.Properties.Id.eq(airLineTaskId)).unique();
    }

    public void delete(PlanTask planTask) throws Exception {
        planTaskDao.delete(planTask);
    }

    public void deleteAll() throws Exception {
        planTaskDao.deleteAll();
    }

}

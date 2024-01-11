package com.syy.moduledbbase.db.utils;

import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.moduleuav.fine.FineAirTaskBean;
import com.syy.moduledbbase.db.entity.moduleuav.plantask.fine.PlanFineAirTask;
import com.syy.moduledbbase.db.greedao.ChildRoutesDao;
import com.syy.moduledbbase.db.greedao.FineAirTaskBeanDao;
import com.syy.moduledbbase.db.greedao.PlanFineAirTaskDao;

import java.util.List;

public class PlanFineAirTaskBeanDaoUtil {

    private final PlanFineAirTaskDao planFineAirTaskDao;

    public PlanFineAirTaskBeanDaoUtil() throws Exception {
        planFineAirTaskDao = DBManager.getInstance().getDaoSession().getPlanFineAirTaskDao();
    }

    public Long insert(PlanFineAirTask planFineAirTask) throws Exception {
        return planFineAirTaskDao.insert(planFineAirTask);
    }

    public void update(PlanFineAirTask planFineAirTask) throws Exception {
        planFineAirTaskDao.update(planFineAirTask);
    }

    public Long insertOrReplace(PlanFineAirTask PlanFineAirTask) throws Exception {
        return planFineAirTaskDao.insertOrReplace(PlanFineAirTask);
    }

    public List<PlanFineAirTask> getPlanFineAirTaskList() throws Exception {
        return planFineAirTaskDao.queryBuilder().list();
    }

    public List<PlanFineAirTask> getPlanFineAirTaskListWithFromType(int fromtype) throws Exception {
        return planFineAirTaskDao.queryBuilder().where(PlanFineAirTaskDao.Properties.Fromtype.eq(fromtype)).list();
    }

//    public List<PlanFineAirTask> getFineAirTaskBeanWithFlyType(int flyType) throws Exception {
//        return planFineAirTaskDao.queryBuilder().where(PlanFineAirTaskDao.Properties.FlyType.eq(flyType)).list();
//    }
//
//    public FineAirTaskBean getFineAirTaskBeanWithTaskId(String airLineTaskId) throws Exception {
//        return planFineAirTaskDao.queryBuilder().where(PlanFineAirTaskDao.Properties.AirLineTaskId.eq(airLineTaskId)).unique();
//    }

    public void delete(PlanFineAirTask PlanFineAirTask) throws Exception {
        planFineAirTaskDao.delete(PlanFineAirTask);
    }

    public void deleteAll() throws Exception {
        planFineAirTaskDao.deleteAll();
    }

}

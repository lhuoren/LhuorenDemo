package com.syy.moduledbbase.db.utils;

import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.moduleuav.plantask.channel.PlanChannelAirTask;
import com.syy.moduledbbase.db.greedao.PlanChannelAirTaskDao;

import java.util.List;

public class PlanChannelAirTaskBeanDaoUtil {

    private final PlanChannelAirTaskDao planChannelAirTaskDao;

    public PlanChannelAirTaskBeanDaoUtil() throws Exception {
        planChannelAirTaskDao = DBManager.getInstance().getDaoSession().getPlanChannelAirTaskDao();
    }

    public Long insert(PlanChannelAirTask planChannelAirTask) throws Exception {
        return planChannelAirTaskDao.insert(planChannelAirTask);
    }

    public void update(PlanChannelAirTask planChannelAirTask) throws Exception {
        planChannelAirTaskDao.update(planChannelAirTask);
    }

    public Long insertOrReplace(PlanChannelAirTask planChannelAirTask) throws Exception {
        return planChannelAirTaskDao.insertOrReplace(planChannelAirTask);
    }

    public List<PlanChannelAirTask> getPlanChannelAirTaskList() throws Exception {
        return planChannelAirTaskDao.queryBuilder().list();
    }

    public List<PlanChannelAirTask> getPlanChannelAirTaskWithFromType(int fromtype) throws Exception {
        return planChannelAirTaskDao.queryBuilder().where(PlanChannelAirTaskDao.Properties.Fromtype.eq(fromtype)).list();
    }

//    public List<PlanChannelAirTask> getFineAirTaskBeanWithFlyType(int flyType) throws Exception {
//        return planChannelAirTaskDao.queryBuilder().where(PlanChannelAirTaskDao.Properties.FlyType.eq(flyType)).list();
//    }
//
//    public PlanChannelAirTask getFineAirTaskBeanWithTaskId(String airLineTaskId) throws Exception {
//        return planChannelAirTaskDao.queryBuilder().where(PlanChannelAirTaskDao.Properties.AirLineTaskId.eq(airLineTaskId)).unique();
//    }

    public void delete(PlanChannelAirTask planChannelAirTask) throws Exception {
        planChannelAirTaskDao.delete(planChannelAirTask);
    }

    public void deleteAll() throws Exception {
        planChannelAirTaskDao.deleteAll();
    }

}

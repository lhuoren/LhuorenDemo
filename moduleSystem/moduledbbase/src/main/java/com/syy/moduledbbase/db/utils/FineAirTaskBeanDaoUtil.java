package com.syy.moduledbbase.db.utils;

import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.moduleuav.fine.FineAirTaskBean;
import com.syy.moduledbbase.db.greedao.FineAirTaskBeanDao;

import java.util.List;

public class FineAirTaskBeanDaoUtil {

    private final FineAirTaskBeanDao fineAirTaskBeanDao;

    public FineAirTaskBeanDaoUtil() throws Exception {
        fineAirTaskBeanDao = DBManager.getInstance().getDaoSession().getFineAirTaskBeanDao();
    }

    public Long insert(FineAirTaskBean fineAirTaskBean) throws Exception {
        return fineAirTaskBeanDao.insert(fineAirTaskBean);
    }

    public void update(FineAirTaskBean fineAirTaskBean) throws Exception {
        fineAirTaskBeanDao.update(fineAirTaskBean);
    }

    public Long insertOrReplace(FineAirTaskBean fineAirTaskBean) throws Exception {
        return fineAirTaskBeanDao.insertOrReplace(fineAirTaskBean);
    }

    public List<FineAirTaskBean> getFineAirTaskBeanWithFlyType(int flyType) throws Exception {
        return fineAirTaskBeanDao.queryBuilder().where(FineAirTaskBeanDao.Properties.FlyType.eq(flyType)).list();
    }

    public FineAirTaskBean getFineAirTaskBeanWithTaskId(String airLineTaskId) throws Exception {
        return fineAirTaskBeanDao.queryBuilder().where(FineAirTaskBeanDao.Properties.AirLineTaskId.eq(airLineTaskId)).unique();
    }

    public void delete(FineAirTaskBean fineAirTaskBean) throws Exception {
        fineAirTaskBeanDao.delete(fineAirTaskBean);
    }

    public void deleteAll() throws Exception {
        fineAirTaskBeanDao.deleteAll();
    }

}

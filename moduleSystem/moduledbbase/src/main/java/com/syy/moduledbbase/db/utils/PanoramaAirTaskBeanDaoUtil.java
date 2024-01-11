package com.syy.moduledbbase.db.utils;

import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.moduleuav.panorama.PanoramaAirTaskBean;
import com.syy.moduledbbase.db.greedao.PanoramaAirTaskBeanDao;

import java.util.List;

public class PanoramaAirTaskBeanDaoUtil {

    private final PanoramaAirTaskBeanDao panoramaAirTaskBeanDao;

    public PanoramaAirTaskBeanDaoUtil() throws Exception {
        panoramaAirTaskBeanDao = DBManager.getInstance().getDaoSession().getPanoramaAirTaskBeanDao();
    }

    public Long insert(PanoramaAirTaskBean panoramaAirTaskBean) throws Exception {
        return panoramaAirTaskBeanDao.insert(panoramaAirTaskBean);
    }

    public void update(PanoramaAirTaskBean panoramaAirTaskBean) throws Exception {
        panoramaAirTaskBeanDao.update(panoramaAirTaskBean);
    }

    public Long insertOrReplace(PanoramaAirTaskBean panoramaAirTaskBean) throws Exception {
        return panoramaAirTaskBeanDao.insertOrReplace(panoramaAirTaskBean);
    }

    public List<PanoramaAirTaskBean> getFineAirTaskBeanWithFlyType(int flyType) throws Exception {
        return panoramaAirTaskBeanDao.queryBuilder().where(PanoramaAirTaskBeanDao.Properties.FlyType.eq(flyType)).list();
    }

    public PanoramaAirTaskBean getFineAirTaskBeanWithTaskId(String airLineTaskId) throws Exception {
        return panoramaAirTaskBeanDao.queryBuilder().where(PanoramaAirTaskBeanDao.Properties.AirLineTaskId.eq(airLineTaskId)).unique();
    }

    public void delete(PanoramaAirTaskBean panoramaAirTaskBean) throws Exception {
        panoramaAirTaskBeanDao.delete(panoramaAirTaskBean);
    }

    public void deleteAll() throws Exception {
        panoramaAirTaskBeanDao.deleteAll();
    }

}

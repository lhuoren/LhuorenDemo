package com.syy.moduledbbase.db.utils;

import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.moduleuav.pointcloud.PointCloudAirTaskBean;
import com.syy.moduledbbase.db.entity.moduleuav.pointcloud.PointCloudDataBean;
import com.syy.moduledbbase.db.greedao.PointCloudAirTaskBeanDao;

import java.util.List;

public class PointCloudAirTaskBeanDaoUtil {

    private final PointCloudAirTaskBeanDao pointCloudAirTaskBeanDao;

    public PointCloudAirTaskBeanDaoUtil() throws Exception {
        pointCloudAirTaskBeanDao = DBManager.getInstance().getDaoSession().getPointCloudAirTaskBeanDao();
    }

    public Long insert(PointCloudAirTaskBean pointCloudAirTaskBean) throws Exception {
        return pointCloudAirTaskBeanDao.insert(pointCloudAirTaskBean);
    }

    public void update(PointCloudAirTaskBean pointCloudAirTaskBean) throws Exception {
        pointCloudAirTaskBeanDao.update(pointCloudAirTaskBean);
    }

    public Long insertOrReplace(PointCloudAirTaskBean pointCloudAirTaskBean) throws Exception {
        return pointCloudAirTaskBeanDao.insertOrReplace(pointCloudAirTaskBean);
    }

    public List<PointCloudAirTaskBean> getFineAirTaskBeanWithFlyType(int flyType) throws Exception {
        return pointCloudAirTaskBeanDao.queryBuilder().where(PointCloudAirTaskBeanDao.Properties.FlyType.eq(flyType)).list();
    }

    public PointCloudAirTaskBean getFineAirTaskBeanWithTaskId(String airLineTaskId) throws Exception {
        return pointCloudAirTaskBeanDao.queryBuilder().where(PointCloudAirTaskBeanDao.Properties.AirLineTaskId.eq(airLineTaskId)).unique();
    }

    public void delete(PointCloudAirTaskBean pointCloudAirTaskBean) throws Exception {
        pointCloudAirTaskBeanDao.delete(pointCloudAirTaskBean);
    }

    public void deleteAll() throws Exception {
        pointCloudAirTaskBeanDao.deleteAll();
    }

}

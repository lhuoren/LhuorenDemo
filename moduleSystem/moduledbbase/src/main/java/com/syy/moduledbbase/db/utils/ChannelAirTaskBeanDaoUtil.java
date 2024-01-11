package com.syy.moduledbbase.db.utils;

import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.moduleuav.channel.ChannelAirTaskBean;
import com.syy.moduledbbase.db.greedao.ChannelAirTaskBeanDao;

import java.util.List;

public class ChannelAirTaskBeanDaoUtil {

    private final ChannelAirTaskBeanDao channelAirTaskBeanDao;

    public ChannelAirTaskBeanDaoUtil() throws Exception {
        channelAirTaskBeanDao = DBManager.getInstance().getDaoSession().getChannelAirTaskBeanDao();
    }

    public Long insert(ChannelAirTaskBean channelAirTaskBean) throws Exception {
        return channelAirTaskBeanDao.insert(channelAirTaskBean);
    }

    public void update(ChannelAirTaskBean channelAirTaskBean) throws Exception {
        channelAirTaskBeanDao.update(channelAirTaskBean);
    }

    public Long insertOrReplace(ChannelAirTaskBean channelAirTaskBean) throws Exception {
        return channelAirTaskBeanDao.insertOrReplace(channelAirTaskBean);
    }

    public List<ChannelAirTaskBean> getFineAirTaskBeanWithFlyType(int flyType) throws Exception {
        return channelAirTaskBeanDao.queryBuilder().where(ChannelAirTaskBeanDao.Properties.FlyType.eq(flyType)).list();
    }

    public ChannelAirTaskBean getFineAirTaskBeanWithTaskId(String airLineTaskId) throws Exception {
        return channelAirTaskBeanDao.queryBuilder().where(ChannelAirTaskBeanDao.Properties.AirLineTaskId.eq(airLineTaskId)).unique();
    }

    public void delete(ChannelAirTaskBean channelAirTaskBean) throws Exception {
        channelAirTaskBeanDao.delete(channelAirTaskBean);
    }

    public void deleteAll() throws Exception {
        channelAirTaskBeanDao.deleteAll();
    }

}

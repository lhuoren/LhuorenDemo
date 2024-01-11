package com.syy.moduledbbase.db.utils;

import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.modulecommon.FlyRecordDetail;
import com.syy.moduledbbase.db.greedao.FlyRecordDetailDao;

public class FlyRecordDetailDaoUtil {

    private FlyRecordDetailDao flyRecordDetailDao;

    public FlyRecordDetailDaoUtil() throws Exception{
        flyRecordDetailDao = DBManager.getInstance().getDaoSession().getFlyRecordDetailDao();
    }

    public Long insert(FlyRecordDetail flyRecordDetail) throws Exception{
        return flyRecordDetailDao.insert(flyRecordDetail);
    }

    public Long insertOrReplace(FlyRecordDetail flyRecordDetail) throws Exception{
        return flyRecordDetailDao.insertOrReplace(flyRecordDetail);
    }

    public void update(FlyRecordDetail flyRecordDetail) throws Exception{
        flyRecordDetailDao.update(flyRecordDetail);
    }

    public FlyRecordDetailDao getFlyRecordDetailDao() {
        return flyRecordDetailDao;
    }

    public void setFlyRecordDetailDao(FlyRecordDetailDao flyRecordDetailDao) {
        this.flyRecordDetailDao = flyRecordDetailDao;
    }
}

package com.syy.moduledbbase.db.utils;

import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.moduleuav.plantask.PlanTask;
import com.syy.moduledbbase.db.entity.moduleuav.plantask.line.ChildRoutes;
import com.syy.moduledbbase.db.greedao.ChildRoutesDao;
import com.syy.moduledbbase.db.greedao.PlanTaskDao;

import java.util.List;

public class ChildRoutesDaoUtil {

    private final ChildRoutesDao childRoutesDao;

    public ChildRoutesDaoUtil() throws Exception {
        childRoutesDao = DBManager.getInstance().getDaoSession().getChildRoutesDao();
    }

    public Long insert(ChildRoutes childRoutes) throws Exception {
        return childRoutesDao.insert(childRoutes);
    }

    public void update(ChildRoutes childRoutes) throws Exception {
        childRoutesDao.update(childRoutes);
    }

    public Long insertOrReplace(ChildRoutes childRoutes) throws Exception {
        return childRoutesDao.insertOrReplace(childRoutes);
    }

    public List<ChildRoutes> getChildRoutesList() throws Exception {
        return childRoutesDao.queryBuilder().list();
    }

    public List<ChildRoutes> getChildRoutesListWithFrom(int fromtype) throws Exception {
        return childRoutesDao.queryBuilder().where(ChildRoutesDao.Properties.Fromtype.eq(fromtype)).list();
    }

    public List<ChildRoutes> getChildRoutesListWithFromTypeAndKeyword(int fromtype, String keyword) throws Exception {
        return childRoutesDao.queryBuilder().where(ChildRoutesDao.Properties.Fromtype.eq(fromtype), ChildRoutesDao.Properties.Name.like("%" + keyword + "%")).list();
    }

    public List<ChildRoutes> getChildRoutesListWithFromTypeAndKeywordTenRec(int fromtype, String keyword, int offset) throws Exception {
        return childRoutesDao.queryBuilder()
                .where(ChildRoutesDao.Properties.Fromtype.eq(fromtype), ChildRoutesDao.Properties.Name.like("%" + keyword + "%"))
                .offset(offset * 3)
                .limit(3)
                .list();
    }

    public ChildRoutes getChildRoutesWithTaskId(String airLineTaskId) throws Exception {
        return childRoutesDao.queryBuilder().where(ChildRoutesDao.Properties.Id.eq(airLineTaskId)).unique();
    }

    public void delete(ChildRoutes childRoutes) throws Exception {
        childRoutesDao.delete(childRoutes);
    }

    public void deleteAll() throws Exception {
        childRoutesDao.deleteAll();
    }

}

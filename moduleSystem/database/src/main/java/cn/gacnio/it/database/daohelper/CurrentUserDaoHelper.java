package cn.gacnio.it.database.daohelper;

import androidx.annotation.Nullable;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import cn.gacnio.it.database.action.BaseDaoAction;
import cn.gacnio.it.database.action.DatabaseManager;
import cn.gacnio.it.database.greendao.CurrentUserDBDao;
import cn.gacnio.it.database.greendao.DaoSession;
import cn.gacnio.it.database.model.CurrentUserDB;

/**
 * <p>
 * 用户管理
 */
public class CurrentUserDaoHelper extends BaseDaoAction<CurrentUserDB, CurrentUserDBDao> {

    public static CurrentUserDaoHelper get() {
        return new CurrentUserDaoHelper();
    }

    @Override
    protected QueryBuilder<CurrentUserDB> getQueryBuilder() {
        CurrentUserDBDao entityDao = getEntityDao();
        if (entityDao != null) {
            return entityDao.queryBuilder();
        } else {
            return null;
        }
    }

    @Override
    protected CurrentUserDBDao getEntityDao() {
        DaoSession daoSession = getDaoSession();
        if (daoSession != null) {
            return daoSession.getCurrentUserDBDao();
        } else {
            return null;
        }
    }

    @Override
    protected DaoSession getDaoSession() {
        return DatabaseManager.get().getBaseDaoSession();
    }
    /**
     * 保存用户
     */
    public long insertCurrentUser(String token, String userId,  String userName, String facePic,String name,String deviceId,String phone) {
        return insertOrReplace(new CurrentUserDB(null, token,userId, userName,  facePic,  name,deviceId,phone));
    }

    /**
     * 保存用户
     */
    public long insertCurrentUser(CurrentUserDB mCuserDb) {
        return insertOrReplace( mCuserDb );
    }


    /**
     * 更新用户
     */
    public void updateCurrentUser(CurrentUserDB currentUserDB) {
        update(currentUserDB);
    }

    /**
     * 当前用户不需要传入参数
     * 当返回null，表示没有用户登陆，如果不为null，直接读取userid或者token参数
     */
    @Nullable
    public CurrentUserDB getCurrentUser() {
        QueryBuilder<CurrentUserDB> queryBuilder = getQueryBuilder();
        if (queryBuilder == null) {
            return null;
        }
        List<CurrentUserDB> cUserDBList = queryBuilder.list();
        if (cUserDBList == null || cUserDBList.isEmpty()) {
            return null;
        } else {
            return cUserDBList.get(0);
        }
    }

}

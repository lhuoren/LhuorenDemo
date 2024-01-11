package cn.gacnio.it.database.daohelper;

import androidx.annotation.Nullable;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import cn.gacnio.it.database.action.BaseDaoAction;
import cn.gacnio.it.database.action.DatabaseManager;
import cn.gacnio.it.database.greendao.DaoSession;
import cn.gacnio.it.database.greendao.ModuleEntityDao;
import cn.gacnio.it.database.model.ModuleEntity;
/**

 * <p>
 * 用户管理
 */
public class ModuleEntityDaoHelper extends BaseDaoAction<ModuleEntity, ModuleEntityDao> {

    public static ModuleEntityDaoHelper get() {
        return new ModuleEntityDaoHelper();
    }

    @Override
    protected QueryBuilder<ModuleEntity> getQueryBuilder() {
        ModuleEntityDao entityDao = getEntityDao();
        if (entityDao != null) {
            return entityDao.queryBuilder();
        } else {
            return null;
        }
    }

    @Override
    protected ModuleEntityDao getEntityDao() {
        DaoSession daoSession = getDaoSession();
        if (daoSession != null) {
            return daoSession.getModuleEntityDao();
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
    public long insertCurrentModule(long id, String name, String className, int resId, int selectedIconId,
                                    int unSelectedIconId,
                                    String animationIcon,
                                  boolean enabled,int order) {
        return insertOrReplace(new ModuleEntity(  id,   name,   className,   resId,
                selectedIconId,
                animationIcon,
                unSelectedIconId,
                enabled,order));
    }

    /**
     * 保存用户
     */
    public long insertCurrentModule(ModuleEntity mCuserDb) {
        return insertOrReplace( mCuserDb );
    }

    /**
     * 保存用户
     */
    public void insertListCurrentModule(List<ModuleEntity> mCuserDb) {
         insertOrReplaceInTx( mCuserDb );
    }


    /**
     * 更新用户
     */
    public void updateCurrentModule(ModuleEntity currentUserDB) {
        update(currentUserDB);
    }

    /**
     * 当前用户不需要传入参数
     * 当返回null，表示没有用户登陆，如果不为null，直接读取userid或者token参数
     */
    @Nullable
    public ModuleEntity getCurrentModule() {
        QueryBuilder<ModuleEntity> queryBuilder = getQueryBuilder();
        if (queryBuilder == null) {
            return null;
        }
        List<ModuleEntity> cUserDBList = queryBuilder.list();
        if (cUserDBList == null || cUserDBList.isEmpty()) {
            return null;
        } else {
            return cUserDBList.get(0);
        }
    }


    /**
     * 当前用户不需要传入参数
     * 当返回null，表示没有用户登陆，如果不为null，直接读取userid或者token参数
     */
    @Nullable
    public List< ModuleEntity> getCurrentModuleList() {
        QueryBuilder<ModuleEntity> queryBuilder = getQueryBuilder();
        if (queryBuilder == null) {
            return null;
        }
        List<ModuleEntity> cUserDBList = queryBuilder.list();
        if (cUserDBList == null || cUserDBList.isEmpty()) {
            return null;
        } else {
            return cUserDBList;
        }
    }

}

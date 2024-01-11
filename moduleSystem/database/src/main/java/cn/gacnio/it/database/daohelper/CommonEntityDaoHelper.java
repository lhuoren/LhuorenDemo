package cn.gacnio.it.database.daohelper;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import org.greenrobot.greendao.query.QueryBuilder;
import java.lang.reflect.Type;
import java.util.List;

import cn.gacnio.it.database.SPUtils;
import cn.gacnio.it.database.action.BaseDaoAction;
import cn.gacnio.it.database.action.DatabaseManager;
import cn.gacnio.it.database.greendao.CommonEntityDao;
import cn.gacnio.it.database.greendao.DaoSession;
import cn.gacnio.it.database.model.CommonEntity;

/**
 * @author heyufei
 * @version 1.0
 * @className CommonEntity
 * @description 公共数据保存,
 * 1。将任意非基本数据类型的bean 以json字符串形式保存
 * 2。根据key、type查询保存的bean
 * @since 2022/8/17 10:40
 */
public class CommonEntityDaoHelper extends BaseDaoAction<CommonEntity, CommonEntityDao> {

    private Gson mGson = new Gson();

    private Object mLock = new Object();

    public static CommonEntityDaoHelper get() {
        return new CommonEntityDaoHelper();
    }

    //默认类型
    private String mDefaultType = " COMMON_DEFAULT_TYPE";

    private CommonEntityDaoHelper() {

    }


    @Override
    protected QueryBuilder<CommonEntity> getQueryBuilder() {
        CommonEntityDao entityDao = getEntityDao();
        if (entityDao != null) {
            return entityDao.queryBuilder();
        } else {
            return null;
        }
    }

    @Override
    protected CommonEntityDao getEntityDao() {
        DaoSession daoSession = getDaoSession();
        if (daoSession != null) {
            return daoSession.getCommonEntityDao();
        } else {
            return null;
        }
    }

    @Override
    protected DaoSession getDaoSession() {
        return DatabaseManager.get().getBaseDaoSession();
    }

    /**
     * 保存公共数据
     * 不支持基本数据类型存储，基础数据类型存储请使用 `SPUtils`
     *
     * @param key   可以理解为操作系统用户id
     * @param type  可以理解为操作系统用户组id
     * @param value 实际存储的bean
     * @return
     */
    public long saveCommonData(String key, String type, Object value) {
        boolean basicDataTypes = false;
        if (value instanceof String) {
            basicDataTypes = true;
        } else if (value instanceof Integer) {
            basicDataTypes = true;

        } else if (value instanceof Float) {
            basicDataTypes = true;

        } else if (value instanceof Double) {
            basicDataTypes = true;

        } else if (value instanceof Long) {
            basicDataTypes = true;

        } else if (value instanceof Byte) {
            basicDataTypes = true;

        } else if (value instanceof Boolean) {
            basicDataTypes = true;

        } else if (value instanceof Character) {
            basicDataTypes = true;
        }
        if (basicDataTypes) {
            throw new IllegalArgumentException("不支持保存基本数据类型");
        }

        String fixType = type;
        if (TextUtils.isEmpty(type)) {
            fixType = mDefaultType;
        }
        if (TextUtils.isEmpty(key) || value == null) {
            throw new NullPointerException("key 或 value 不能为空");
        }
        String jsonStr = mGson.toJson(value);

        return insertOrReplace(new CommonEntity(getEntityId(key, fixType), key, fixType, jsonStr));
    }

    /**
     * 保存公共数据
     * 不支持基本数据类型存储，基础数据类型存储请使用 `SPUtils`
     *
     * @param key   保存组建
     * @param value 实际存储的bean
     * @return
     */
    public long saveCommonData(String key, Object value) {
        return saveCommonData(key, null, value);
    }

    /**
     * 查询存储的数据
     *
     * @param key      查询key，对应存储时的key
     * @param classOfT bean泛形
     * @return
     */
    @Nullable
    public <T> T getCommonData(String key, Class<T> classOfT) {
        return getCommonData(key, null, classOfT);
    }

    /**
     * 查询存储的数据
     *
     * @param key    查询key，对应存储时的key
     * @param typeOf 具体类型
     * @return
     */
    @Nullable
    public <T> T getCommonData(String key, Type typeOf) {
        return getCommonData(key, null, typeOf);
    }

    @Nullable
    public <T> T getCommonData(String key, String type, Type typeOf) {
        if (TextUtils.isEmpty(key)) {
            throw new NullPointerException("查询key 不允许null");
        }

        String fixType = type;
        if (TextUtils.isEmpty(type)) {
            fixType = mDefaultType;
        }

        QueryBuilder<CommonEntity> queryBuilder = getQueryBuilder();
        if (queryBuilder == null) {
            return null;
        }
        List<CommonEntity> list = queryBuilder
                .where(CommonEntityDao.Properties.Key.eq(key),
                        CommonEntityDao.Properties.Type.eq(fixType))
                .orderDesc(CommonEntityDao.Properties.Id)
                .list();
        if (list.size() > 0) {
            try {
                return mGson.fromJson(list.get(0).getJsonStr(), typeOf);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Nullable
    public <T> T getCommonData(String key, String type, Class<T> classOfT) {
        return getCommonData(key, type, (Type) classOfT);
    }


    /**
     * 生成主键
     *
     * @param key
     * @param type
     * @return primaryKey
     */
    private long getEntityId(String key, String type) {
        synchronized (mLock) {
            String spKey = "CommonEntityDaoHelper_" + key + "_" + type;
            long pk = SPUtils.getLong(spKey, -1L);
            if (pk < 0) {
//                pk = TimeAsyncManager.getCurrentTimeMillis();
                pk = System.currentTimeMillis();
                SPUtils.put(spKey, pk);

            }
            return pk;
        }
    }
}

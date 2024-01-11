package cn.gacnio.it.database.action;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import cn.gacnio.it.database.greendao.DaoSession;




public abstract class BaseDaoAction<T, G extends AbstractDao> implements DaoAction<T> {

    protected abstract QueryBuilder<T> getQueryBuilder();

    protected abstract G getEntityDao();

    protected abstract DaoSession getDaoSession();

    /**
     * Function: insert()
     * NOTE: 插入的数据的主键值，不可以跟数据库中已有数据的主键值相同，否则会导致报异常
     * android.database.sqlite.SQLiteConstraintException
     **/
    @Override
    public final long insert(T t) {
        G entityDao = getEntityDao();
        if (entityDao == null) {
            return 0;
        }

        return entityDao.insert(t);
    }

    @SafeVarargs
    @Override
    public final void insertInTx(T... array) {
        G entityDao = getEntityDao();
        if (entityDao == null) {
            return;
        }

        entityDao.insertInTx(array);
    }

    @Override
    public void insertInTx(Iterable<T> list) {
        G entityDao = getEntityDao();
        if (entityDao == null) {
            return;
        }

        entityDao.insertInTx(list);
    }

    @Override
    public void update(T t) {
        G entityDao = getEntityDao();
        if (entityDao != null) {
            entityDao.update(t);
        }
    }

    /**
     * Function: insertOrReplace()
     * NOTE: 插入的数据的主键值，如果跟数据库中已有数据的主键值相同，会更新数据库中的数据；不同才会写
     * 入新的数据项
     **/
    @Override
    public long insertOrReplace(T t) {
        G entityDao = getEntityDao();
        if (entityDao == null) {
            return 0;
        }

        return entityDao.insertOrReplace(t);
    }

    @Override
    public void insertOrReplaceInTx(T... array) {
        G entityDao = getEntityDao();
        if (entityDao == null) {
            return;
        }

        entityDao.insertOrReplaceInTx(array);
    }

    @Override
    public void insertOrReplaceInTx(Iterable<T> list) {
        G entityDao = getEntityDao();
        if (entityDao == null) {
            return;
        }

        entityDao.insertOrReplaceInTx(list);
    }

    @Override
    public final long count() {
        G entityDao = getEntityDao();
        if (entityDao == null) {
            return 0;
        }

        return entityDao.count();
    }

    @Override
    public final List<T> loadAll() {
        G entityDao = getEntityDao();
        if (entityDao == null) {
            return null;
        }

        return entityDao.loadAll();
    }

    @Override
    public final void deleteAll() {
        G entityDao = getEntityDao();
        if (entityDao != null) {
            entityDao.deleteAll();
        }
    }

    /**
     * Function: deleteByKey()
     * 按键值来删除表项
     * Params:
     * Object key, 要删除项的键值
     **/
    @Override
    public final void deleteByKey(Object key) {
        G entityDao = getEntityDao();
        if (entityDao != null) {
            entityDao.deleteByKey(key);
        }
    }

    @Override
    public final void deleteByKeyAsync(final Object key) {
        new Thread(() -> deleteByKey(key)).start();
    }

    @Override
    public final void deleteAllAsync() {
        new Thread(this::deleteAll).start();
    }

    @Override
    public final void insertAsync(T t) {
        new Thread(() -> insert(t)).start();
    }

    @Override
    public final void insertOrReplaceAsync(T t) {
        new Thread(() -> insertOrReplace(t)).start();
    }

    public void updateAllAsync(final Iterable<T> list) {
        new Thread(() -> {
            deleteAll();

            for (T t : list) {
                insert(t);
            }
        }).start();
    }

}

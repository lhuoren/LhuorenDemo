package cn.gacnio.it.database.action;

import java.util.List;


interface DaoAction<T> {

    /* 同步方法 */

    /**
     * Function: insert()
     * NOTE: 插入的数据的主键值，不可以跟数据库中已有数据的主键值相同，否则会导致报异常
     * android.database.sqlite.SQLiteConstraintException
     **/
    long insert(T t);

    void insertInTx(T... array);

    void insertInTx(Iterable<T> list);

    void update(T t);

    /**
     * Function: insertOrReplace()
     * NOTE: 插入的数据的主键值，如果跟数据库中已有数据的主键值相同，会更新数据库中的数据；不同才会写
     * 入新的数据项
     **/
    long insertOrReplace(T t);

    void insertOrReplaceInTx(T... array);

    void insertOrReplaceInTx(Iterable<T> list);

    /**
     * func: 数据表中数据项的数量
     **/
    long count();

    /**
     * func: 获取数据表中所有的数据
     **/
    List<T> loadAll();

    void deleteAll();

    /**
     * Function: deleteByKey()
     * 按键值来删除表项
     * Params:
     * Object key, 要删除项的键值
     **/
    void deleteByKey(Object key);

    /* 异步方法 */
    void deleteAllAsync();

    void updateAllAsync(Iterable<T> list);

    void insertAsync(T t);

    void insertOrReplaceAsync(T t);

    void deleteByKeyAsync(Object key);

    interface AsyncDaoCallback<T> {
        void onResult(List<T> list);
    }
}

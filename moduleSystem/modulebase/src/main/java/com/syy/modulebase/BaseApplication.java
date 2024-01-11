package com.syy.modulebase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
//import com.syy.moduledbbase.db.greedao.DaoMaster;
//import com.syy.moduledbbase.db.greedao.DaoSession;
//
//import org.greenrobot.greendao.database.Database;

/**
 * =====作者=====
 * lhuoren
 * =====时间=====
 * 2017/10/10.
 */
public class BaseApplication extends MultiDexApplication {
    @SuppressLint("StaticFieldLeak")
    private static Context instanceContext;
    //    private static DaoSession daoSession;
    //ARouter 调试开关
    private boolean isDebugARouter = true;
//    private static DaoSession daoSession;
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instanceContext = this;
        setApplication(this);

        if(isDebugARouter){
            //下面两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();//打印日志
            //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！
            // 线上版本需要关闭，否则有安全风险)
            ARouter.openDebug();
        }
        //官方建议推荐在Application中初始化
        ARouter.init(this);

//        try {
//            //配置数据库
//            setupDatabase();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    /**
     * 配置数据库
     */
//    private void setupDatabase() {
//
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "easy_smart_patrol.db", null);
////        SQLiteDatabase db = helper.getWritableDatabase();
//        Database db = helper.getEncryptedWritableDb("Abc123#456");
//        DaoMaster daoMaster = new DaoMaster(db);
//        daoSession = daoMaster.newSession();
//    }
//
//    public static DaoSession getDaoInstant() {
//        return daoSession;
//    }

    /**
     * 获取 application 的context
     *
     * @return
     */
    public static Context getInstanceContext() {
        return instanceContext;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }

    /**
     * 当宿主没有继承自该Application时,可以使用set方法进行初始化baseApplication
     */
    private void setApplication(@NonNull BaseApplication application) {
        instance = application;
        application
                .registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(@NonNull Activity activity,
                                                  @Nullable Bundle savedInstanceState) {
                        //AppManager.getAppManager().addActivity(activity);
                    }

                    @Override
                    public void onActivityStarted(@NonNull Activity activity) {

                    }

                    @Override
                    public void onActivityResumed(@NonNull Activity activity) {

                    }

                    @Override
                    public void onActivityPaused(@NonNull Activity activity) {

                    }

                    @Override
                    public void onActivityStopped(@NonNull Activity activity) {

                    }

                    @Override
                    public void onActivitySaveInstanceState(
                            @NonNull Activity activity, @NonNull Bundle outState) {

                    }

                    @Override
                    public void onActivityDestroyed(@NonNull Activity activity) {
                        //AppManager.getAppManager().removeActivity(activity);
                    }
                });
    }

    /**
     * 获得当前app运行的Application
     */
    public static BaseApplication getInstance() {
        if (instance == null) {
            throw new NullPointerException(
                    "please inherit BaseApplication or call setApplication.");
        }
        return instance;
    }

}

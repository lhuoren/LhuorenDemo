package com.syy.moduledbbase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context instanceContext;

    private static BaseApplication instance;

    private static Boolean componentHasInit = false;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instanceContext = this;
        setApplication(this);

    }

    public static Boolean isComponentInit() {
        return componentHasInit;
    }

    /**
     * 获取 application 的context
     *
     * @return
     */
    public static Context getInstanceContext() {
        return instanceContext;
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

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private <T> List<T> copyIterator(Iterator<T> iter) {
        List<T> copy = new ArrayList<T>();
        while (iter != null && iter.hasNext())
            copy.add(iter.next());
        return copy;
    }

}

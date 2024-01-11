package com.gac.nioapp.test;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.BuildConfig;
import com.gac.nioapp.test.public_component.IApplicationLifeCycleService;
import com.gac.nioapp.test.utils.AppStorage;
import com.gac.nioapp.test.utils.GwServiceLoader;
import com.gacnio.toolkit.public_component.IComponentInitializationService;
import com.gacnio.toolkit.xlog.XLog;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseApplication extends Application {

    private static BaseApplication instance;

    private static Boolean componentHasInit = false;

    List<IApplicationLifeCycleService> applicationLifeCycleServiceIterator = copyIterator(GwServiceLoader.INSTANCE.load(IApplicationLifeCycleService.class));


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        for (IApplicationLifeCycleService iApplicationLifeCycleService : applicationLifeCycleServiceIterator) {
            iApplicationLifeCycleService.attachBaseContext(base);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MMKV.initialize(this);

        setApplication(this);

        for (IApplicationLifeCycleService iApplicationLifeCycleService : applicationLifeCycleServiceIterator) {
            iApplicationLifeCycleService.onCreate();
        }
        //各个组件初始化
        componentInit();
    }

    public static Boolean isComponentInit() {
        return componentHasInit;
    }

    public void componentInit() {

        if (componentHasInit) {
            return;
        }

        boolean isMainProcess = true;
        String processName = "";
        String mainProcessName = getPackageName();
        try {
            if (AppStorage.INSTANCE.agreeAgreement()) {
                ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
                List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
                int myPid = android.os.Process.myPid();
                for (ActivityManager.RunningAppProcessInfo info : processInfos) {
                    if (info.pid == myPid) {
                        if (mainProcessName.equals(info.processName)) {
                            isMainProcess = true;
                        } else {
                            isMainProcess = false;
                        }
                        processName = info.processName;
                    }
                }

                if (!isMainProcess) {
                    String pName = processName.replace(mainProcessName + ":", "");
                    XLog.init(this, BuildConfig.DEBUG, pName);
                }

                Iterator<IComponentInitializationService> initializationServiceIterator = GwServiceLoader.INSTANCE.load(IComponentInitializationService.class);

                android.os.Process.myPid();
                if (initializationServiceIterator != null) {
                    while (initializationServiceIterator.hasNext()) {
                        initializationServiceIterator.next().init(this, isMainProcess, processName);
                    }
                }
                Log.d("BaseApplication", "componentInit: ------------>>>>>>>" + processName);
                componentHasInit = true;

                //日志系统
                XLog.init(this, BuildConfig.DEBUG);
            }
        } catch (Exception e) {
            //无法处理因为 attachBaseContext也会调用到，此时确实未进行初始化
            e.printStackTrace();
        }

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
        for (IApplicationLifeCycleService iApplicationLifeCycleService : applicationLifeCycleServiceIterator) {
            iApplicationLifeCycleService.onTerminate();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (IApplicationLifeCycleService iApplicationLifeCycleService : applicationLifeCycleServiceIterator) {
            iApplicationLifeCycleService.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for (IApplicationLifeCycleService iApplicationLifeCycleService : applicationLifeCycleServiceIterator) {
            iApplicationLifeCycleService.onTrimMemory(level);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        for (IApplicationLifeCycleService iApplicationLifeCycleService : applicationLifeCycleServiceIterator) {
            iApplicationLifeCycleService.onConfigurationChanged(newConfig);
        }
    }

    private <T> List<T> copyIterator(Iterator<T> iter) {
        List<T> copy = new ArrayList<T>();
        while (iter != null && iter.hasNext())
            copy.add(iter.next());
        return copy;
    }

}

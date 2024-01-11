package com.syy.modulebase.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Sai on 2018/3/20.
 * App相关数据管理类
 */

public class AppInfoManager {
    private AppInfoManager() {
    }

    private Application context;
    private Env env;
    public int mainTabBarHeight = 0;
    /**
     * 当前版本号
     */
    public static int versionCode;
    /**
     * 当前版本名
     */
    public static String versionName;
    /**
     * 设备型号，如mi 3
     */
    private String mobileModel = AppUtil.getMobileModel();
    /**
     * 渠道来源，如xiaomi
     */
    private static String channelSource="default";

    /**
     * 传给后端当前系统模式 1:默认，2:暗夜
     */
    private static int darkMode = 1;

    public void initAppVersionInfo(int code, String name) {
        versionCode = code;
        versionName = name;
    }

    public String getDarkMode() {
        return darkMode + "";
    }

    public void setDarkMode(boolean isDarkMode) {
        if (isDarkMode) {
            this.darkMode = 2;
        } else {
            this.darkMode = 1;
        }
    }


    private static class AppInfoManagerInstance {
        private static final AppInfoManager INSTANCE = new AppInfoManager();
    }

    public static AppInfoManager getInstance() {
        return AppInfoManagerInstance.INSTANCE;
    }

    public void init(Application context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public Application getApplication() {
        return context;
    }

    public Context getAppContext() {
        return context.getApplicationContext();
    }

    public String getMobileModel() {
        return mobileModel;
    }

    public String getChannelSource() {
        return channelSource;
    }

    public void setChannelSource(String channelSource) {
        AppInfoManager.channelSource = channelSource;
    }

    public void setEnv(Env env) {
        this.env = env;
    }

    public Env getEnv() {
        return env;
    }

    public enum Env {
        //test 测试
        //
        //stg 预发布
        //
        //dev 开发
        //
        //prod 生产
        TEST(0, "test"),
        PRE(1, "stg"),
        DEV(2, "dev"),
        PROD(3, "prod");
        private int code;
        private String env;

        Env(int code, String env) {
            this.code = code;
            this.env = env;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getEnv() {
            return env;
        }

        public void setEnv(String env) {
            this.env = env;
        }
    }
}

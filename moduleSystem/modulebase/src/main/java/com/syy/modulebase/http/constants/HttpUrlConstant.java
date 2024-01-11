package com.syy.modulebase.http.constants;


import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.utils.TextUtils;

import cn.gacnio.it.database.SPUtils;

/**
 * @Description：url 管理
 * @Author：Sai
 * @Date：2019/3/11 13:31
 */
public class HttpUrlConstant {
    private static String APPURL;
    private static String H5_URL;
    private static String YG_HOST;
    public static final String KEY_CURRENT_ENVIRONMENT = "current_environment";
    public static final String KEY_CURRENT_H5_ENVIRONMENT = "current_h5_environment";
    public static final String KEY_CURRENT_YG_ENVIRONMENT = "current_yg_environment";
    public static final String KEY_CURRENT_AUTH_BASE = "current_auth_base";

    private static final String[] cache = new String[]{null, null, null, null};

    public static void initGlobalHost(String url) {
        APPURL = url;
    }

    public static void initH5Host(String url) {
        H5_URL = url;
    }

    public static String getH5Url() {
        return getCacheUrl(1) != null ? cache[1] : H5_URL;
    }

    public static String getAPPURL() {
        return getCacheUrl(0) != null ? cache[0] : APPURL;
    }

    public static String getYgHost() {
        return getCacheUrl(2) != null ? cache[2] : YG_HOST;
    }

    public static String getAuthBase() {
        return getCacheUrl(3) != null ? cache[3] : HttpKeyConstant.AUTHORIZATION_VALUE_DEFAULT;
    }

    public static void initYgHost(String url) {
        YG_HOST = url;
    }

    public static void setAPPURL(String APPURL) {
        HttpUrlConstant.APPURL = APPURL;
    }

    ////app更新////
    public static final String APPID = "1";
    public static final String SYSTEMID = "1";
    /////////


    public static String webVersion = "1.0.0";
    public static String serverVersion = "1.0.0";

    /**
     * debug模式下缓存的url
     *
     * @param type
     * @return
     */
    private static String getCacheUrl(int type) {
        if (BuildConfig.DEBUG) {
            if (TextUtils.isEmpty(cache[type])) {
                switch (type) {
                    case 0:
                        cache[type] = SPUtils.getString(HttpUrlConstant.KEY_CURRENT_ENVIRONMENT, APPURL);
                        break;
                    case 1:
                        cache[type] = SPUtils.getString(HttpUrlConstant.KEY_CURRENT_H5_ENVIRONMENT, H5_URL);
                        break;
                    case 2:
                        cache[type] = SPUtils.getString(HttpUrlConstant.KEY_CURRENT_YG_ENVIRONMENT, YG_HOST);
                        break;
                    case 3:
                        cache[type] = SPUtils.getString(HttpUrlConstant.KEY_CURRENT_AUTH_BASE, HttpKeyConstant.AUTHORIZATION_VALUE_DEFAULT);
                        break;
                    default:
                        cache[type] = SPUtils.getString(HttpUrlConstant.KEY_CURRENT_AUTH_BASE, HttpKeyConstant.AUTHORIZATION_VALUE_DEFAULT);
                        break;
                }
            }
        }
        return cache[type];
    }
}

package com.syy.modulebase.utils;

import com.alibaba.android.arouter.BuildConfig;

import cn.gacnio.it.database.SPUtils;

/**
 * @package： com.gacnio.hycan
 * @describe：
 * @author： liming
 * @time： 2019-08-22 12:01
 * @e-mail： liming@gac-nio.com
 */
public class AppConstant {
    /**
     * 小程序跳转的版本类型，见app的gradle配置
     */
    public static int MINIPROGRAM_TYPE_TARGET;
    public static String MEGA_APPID;
    public static String MEGA_APPSECRET;
    private static int MEGA_SERVER_TYPE;
    public static String SIGN_ID;
    public static String SIGN_SECURITY;
    public static String WEIXIN_APPID;
    public static String WEIXIN_GH_APPID;
    public static String CODEPUSH_KEY;
    public static String MOOR_ACCESSID;
    public static String BUGLY_APPID;

    public static final String DEBUG_MEGASERVERTYPE = "DEBUG_MegaServerType";


    private static final String[] cacheStr = new String[]{null};
    private static final int[] cacheInt = new int[]{-1};

    public static int getMegaServerType() {
        return getCache(0) >= 0 ? cacheInt[0] : MEGA_SERVER_TYPE;
    }

    public static void setMegaServerType(int megaServerType) {
        MEGA_SERVER_TYPE = megaServerType;
    }

    private static int getCache(int position) {
        if (cacheInt[position] < 0) {
            if (BuildConfig.DEBUG) {
                cacheInt[position] = SPUtils.getInt(DEBUG_MEGASERVERTYPE, -1);
            }
        }
        return cacheInt[position];
    }
}

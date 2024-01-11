package com.gac.nioapp.test.utils;

import com.mapbox.android.core.BuildConfig;

import cn.gacnio.it.database.SPUtils;

public class ReactNativeDevConstant {

    // 1 : 关闭, 2 : 开启 rn dev
    public static final String KEY_CURRENT_RN_DEV = "current_rn_dev";

    private static Boolean cacheRnDev = null;

    private static Boolean buildOnJenkins = null;

    public final static int STATUS_CLOSED = 1;
    public final static int STATUS_OPEN = 2;


    public static void init(Boolean buildOnJenkins) {
        ReactNativeDevConstant.buildOnJenkins = buildOnJenkins;
    }

    public static boolean isRNDev() {
        if (BuildConfig.DEBUG) {

            if (cacheRnDev != null)
                return cacheRnDev;

            int rnDev = SPUtils.getInt(KEY_CURRENT_RN_DEV, -1);

            if (rnDev > 0) {
                cacheRnDev = rnDev == STATUS_OPEN;
                return cacheRnDev;
            }

            if (buildOnJenkins != null) {
                //jenkins 构建默认不开RNDev
                return !buildOnJenkins;
            }

        }

        return BuildConfig.DEBUG;
    }


    public static void toggle() {
        boolean isRnDev = isRNDev();
        cacheRnDev = !isRnDev;
        SPUtils.put(KEY_CURRENT_RN_DEV, cacheRnDev ? STATUS_OPEN : STATUS_CLOSED);
    }


}

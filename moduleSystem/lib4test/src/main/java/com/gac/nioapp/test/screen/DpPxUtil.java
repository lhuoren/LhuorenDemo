package com.gac.nioapp.test.screen;

import com.gac.nioapp.test.BaseApplication;

/**
 * 像素密度计算工具
 * 因为app做了375适配,需要在以下时机之后使用
 * 1, BaseApplication.getInstance() 初始化后
 * 2, DensityUtil.setDensity() 初始后使用
 */
@SuppressWarnings("unused")
public class DpPxUtil {

    public float density;


    public DpPxUtil() {
//        density = Resources.getSystem().getDisplayMetrics().density;
        density = BaseApplication.getInstance().getResources().getDisplayMetrics().density;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue 虚拟像素
     * @return 像素
     */
    public static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * BaseApplication.getInstance().getResources().getDisplayMetrics().density);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param pxValue 像素
     * @return 虚拟像素
     */
    public static float px2dp(int pxValue) {
        return (pxValue / BaseApplication.getInstance().getResources().getDisplayMetrics().density);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue 虚拟像素
     * @return 像素
     */
    public int dip2px(float dpValue) {
        return (int) (0.5f + dpValue * density);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param pxValue 像素
     * @return 虚拟像素
     */
    public float px2dip(int pxValue) {
        return (pxValue / density);
    }
}  
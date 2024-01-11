package com.gac.nioapp.test.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @package： com.hycan.community.utils
 * @describe： 参考今日头条的屏幕适配方案，ui给过来的屏幕宽度是375dp,目前只用到UED_WIDTH，部分页面用到UED_HEIGHT
 * @author： liming
 * @time： 2019/4/2 12:00 PM
 * @e-mail： liming@gac-nio.com
 */
public class DensityUtil {
    private static float appDensity;
    private static float appScaledDensity;
    private static DisplayMetrics appDisplayMetrics;
    private static int barHeight;
    public final static String WIDTH = "width";
    public final static String HEIGHT = "height";
    /**
     * 按667偏小
     */
    @Deprecated
    public final static float UED_HEIGHT = 667f;
    public final static float UED_WIDTH = 375f;

    /**
     * 在Application里初始化一下
     *
     * @param application
     */
    public static void setDensity(@NonNull final Application application) {
        //获取application的DisplayMetrics
        appDisplayMetrics = application.getResources().getDisplayMetrics();
        //获取状态栏高度
        barHeight = getStatusBarHeight(application);
        if (appDensity == 0) {
            //初始化的时候赋值
            appDensity = appDisplayMetrics.density;
            appScaledDensity = appDisplayMetrics.scaledDensity;
            //添加字体变化的监听
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    //字体改变后,将appScaledDensity重新赋值
                    if (newConfig != null && newConfig.fontScale > 0) {
                        appScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {
                }
            });
        }
    }

    /**
     * 此方法在BaseActivity中做初始化(如果不封装BaseActivity的话,直接用下面那个方法就好)
     * 在setContentView()之前设置
     *
     * @param activity
     */
    public static void setDefault(Activity activity) {

        int orientation = Configuration.ORIENTATION_PORTRAIT;

        try {
            orientation = activity.getResources().getConfiguration().orientation;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setAppOrientation(activity, HEIGHT);
        } else {
            setAppOrientation(activity, WIDTH);
        }
    }

    /**
     * 此方法用于在某一个Activity里面更改适配的方向
     * 在setContentView()之前设置
     *
     * @param activity
     * @param orientation
     */
    public static void setOrientation(Activity activity, String orientation) {
        setAppOrientation(activity, orientation);
    }


    /**
     * targetDensity
     * targetScaledDensity
     * targetDensityDpi
     * 这三个参数是统一修改过后的值
     * orientation:方向值,传入width或height
     */
    public static DisplayMetrics getContextDensity(@Nullable Context context) {
        float targetDensity;
        //设计图的宽度 单位:dp
        targetDensity = appDisplayMetrics.widthPixels / UED_WIDTH;

        float targetScaledDensity = targetDensity * (appScaledDensity / appDensity);
        int targetDensityDpi = (int) (160 * targetDensity);
        /**
         *
         * 最后在这里将修改过后的值赋给系统参数
         * 只修改Activity的density值
         */
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if(context != null){
            displayMetrics.setTo( context.getResources().getDisplayMetrics());
        }else{
            displayMetrics.setToDefaults();
        }

        displayMetrics.density = targetDensity;
        displayMetrics.scaledDensity = targetScaledDensity;
        displayMetrics.densityDpi = targetDensityDpi;
        return displayMetrics;
    }
    /**
     * targetDensity
     * targetScaledDensity
     * targetDensityDpi
     * 这三个参数是统一修改过后的值
     * orientation:方向值,传入width或height
     */
    private static void setAppOrientation(@Nullable Activity activity, String orientation) {
        float targetDensity;
        if (orientation.equals(HEIGHT)) {
            //设计图的高度 单位:dp
            // 横屏下 , appDisplayMetrics.heightPixels = 屏幕短边
            targetDensity = appDisplayMetrics.heightPixels / UED_WIDTH;
        } else {
            //设计图的宽度 单位:dp
            targetDensity = appDisplayMetrics.widthPixels / UED_WIDTH;
        }
        float targetScaledDensity = targetDensity * (appScaledDensity / appDensity);
        int targetDensityDpi = (int) (160 * targetDensity);
        /**
         *
         * 最后在这里将修改过后的值赋给系统参数
         * 只修改Activity的density值
         */
        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}

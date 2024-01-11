package com.gac.nioapp.test.view.topbar;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

/**
 * @Description：顶部适配padding的沉浸式APPBarLayout
 * @Author：Sai
 * @Date：2019/3/13 11:52
 */
public class AppBarLayout extends com.google.android.material.appbar.AppBarLayout {
    public AppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //适配沉浸式，主动加padding顶部
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if(resourceId>0) {
                statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            }
        }else{
            //低版本 直接设置0
            statusBarHeight = 0;
        }
        setPadding(getPaddingLeft(), statusBarHeight, getPaddingRight(), getPaddingBottom());
    }

}

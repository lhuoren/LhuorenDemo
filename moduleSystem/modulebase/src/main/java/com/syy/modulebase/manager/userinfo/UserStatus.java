package com.syy.modulebase.manager.userinfo;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;

/**
 * Created by Sai on 2018/3/21.
 */

public interface UserStatus {
    /**
     * 用于需要验证登录状态的跳转，未登录会先去登录，登录完成自动跳到原需要跳转的页面。登录了直接跳转
     * @param activity
     * @param intent
     */
    void startActivity(Activity activity, Intent intent);
    /**
     * 用于需要验证登录状态的跳转，未登录会先去登录，登录完成自动跳到原需要跳转的页面。登录了直接跳转
     * @param activity
     * @param intent
     * @param requestCode ActivityForResult 的 code
     */
    void startActivity(Activity activity, Intent intent,int requestCode);

    void startActivity(Fragment fragment, Intent intent, int requestCode);

    boolean isLogin();
}

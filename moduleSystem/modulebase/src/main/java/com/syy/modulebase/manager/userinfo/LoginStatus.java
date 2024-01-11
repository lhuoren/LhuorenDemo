package com.syy.modulebase.manager.userinfo;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;

/**
 * Created by Sai on 2018/3/21.
 */

public class LoginStatus implements UserStatus{

    @Override
    public void startActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }

    @Override
    public void startActivity(Activity activity, Intent intent, int requestCode) {
        activity.startActivityForResult(intent,requestCode);
    }

    @Override
    public void startActivity(Fragment fragment, Intent intent, int requestCode) {
        fragment.startActivityForResult(intent,requestCode);
    }

    @Override
    public boolean isLogin() {
        return true;
    }
}

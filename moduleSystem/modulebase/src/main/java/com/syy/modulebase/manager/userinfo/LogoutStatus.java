package com.syy.modulebase.manager.userinfo;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.syy.modulebase.manager.helper.Key2LoginHelper;

/**
 * Created by Sai on 2018/3/21.
 */

public class LogoutStatus implements UserStatus{

    @Override
    public void startActivity(Activity activity, Intent intent) {
//        Intent intentLogin = new Intent(activity, LoginActivity.class);
//        intentLogin.putExtra(LoginActivity.INTENTKEY, intent);
//        activity.startActivity(intentLogin);
        Key2LoginHelper.startActivity1(activity,intent);
    }

    @Override
    public void startActivity(Activity activity, Intent intent, int requestCode) {
//        Intent intentLogin = new Intent(activity, LoginActivity.class);
//        intentLogin.putExtra(LoginActivity.INTENTKEY, intent);
//        activity.startActivityForResult(intentLogin, requestCode);
        Key2LoginHelper.startActivity2(activity,intent,requestCode);
    }

    @Override
    public void startActivity(Fragment fragment, Intent intent, int requestCode) {
//        Intent intentLogin = new Intent(fragment.getContext(), LoginActivity.class);
//        intentLogin.putExtra(LoginActivity.INTENTKEY, intent);
//        fragment.startActivityForResult(intentLogin, requestCode);
//        ARouterManager.getInstance().startActivityForResult(RouterConstant.PATH_TO_LOGIN,intent,fragment.getActivity(),requestCode);
        Key2LoginHelper.startActivity3(fragment,intent,requestCode);
    }

    @Override
    public boolean isLogin() {
        return false;
    }
}

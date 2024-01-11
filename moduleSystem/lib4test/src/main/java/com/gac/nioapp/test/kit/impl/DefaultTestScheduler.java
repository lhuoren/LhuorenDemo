package com.gac.nioapp.test.kit.impl;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

/**
 * FileName: DefaultTestScheduler
 * Author: heyufei
 * Date: 2021/4/10 11:03
 * Description: 默认未实现的测试代码
 *
 * @Version: 1.0
 */
public class DefaultTestScheduler implements GacnioTestScheduler {
    private final String TAG = "DefaultTestScheduler";

    private final String ENGINEER_NAME;

    public DefaultTestScheduler(String engineer_name) {
        ENGINEER_NAME = engineer_name;
    }

    @Override
    public void onDragFloatBtnClick(Activity context) {
        Log.w(TAG, ENGINEER_NAME + "onDragFloatBtnClick: ----");
    }

    @Override
    public void onDragFloatBtnLongClick(Activity context) {
        Log.w(TAG, ENGINEER_NAME + "onDragFloatBtnLongClick: ----");

    }

    @Override
    public void onNextDragFloatBtnClick(Activity context) {
        Log.w(TAG, ENGINEER_NAME + "onNextDragFloatBtnClick: ----");
    }

    @Override
    public void init(Context context) {
        Log.w(TAG, ENGINEER_NAME + "init: ----");
    }
}

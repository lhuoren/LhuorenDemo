package com.gac.nioapp.test.kit.impl;


import android.app.Activity;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * FileName: GacnioTestScheduler
 * Author: heyufei
 * Date: 2021/4/10 10:42
 * Description: 测试调度类(不同开发人员可自行实现一个)
 *
 * @Version: 1.0
 */
public interface GacnioTestScheduler extends IProvider {
    /**
     * ui测试1
     *
     * @param context
     */
    void onDragFloatBtnClick(Activity context);

    /**
     * ui测试2
     *
     * @param context
     */
    void onDragFloatBtnLongClick(Activity context);

    /**
     * ui测试3
     *
     * @param context
     */
    void onNextDragFloatBtnClick(Activity context);
}

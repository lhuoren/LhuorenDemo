package com.syy.modulebase.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.syy.modulebase.permission.ContextHelper;

/**
 * UI通用方法类
 */
public class ToastUtil {

    private static Toast mToast;

    public static final void toastLongMessage(final String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                mToast = Toast.makeText(ContextHelper.getAppContext(), message,
                        Toast.LENGTH_LONG);
                mToast.show();
            }
        });
    }


    public static final void toastShortMessage(final String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                mToast = Toast.makeText(ContextHelper.getAppContext(), message,
                        Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }

    public void showToast(String msg, int duration) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        View view = Toast.makeText(ContextHelper.getAppContext(), "", duration).getView();
        Toast toast = new Toast(ContextHelper.getAppContext());
        toast.setView(view);
        toast.setText(msg);
        toast.setDuration(duration);
        toast.show();

    }
}

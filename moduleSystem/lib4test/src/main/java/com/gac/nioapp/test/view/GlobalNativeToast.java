package com.gac.nioapp.test.view;

import android.annotation.SuppressLint;
import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * @package： com.gac.commonui.widget
 * @describe： 避免多次显示同一个 toast
 * @author： liming
 * @time： 2020/7/13 2:08 PM
 * @e-mail： liming@gac-nio.com
 */
public class GlobalNativeToast {
    private static Toast toast = null;

    @SuppressLint("ShowToast")
    public static void show(Application application, CharSequence text, int toastDuration) {
        /**
         * HYCAN wifi经常访问生产环境会出现异常，老板总是在此wifi下使用，未知异常是后台返回的一个异常之外的异常，前端不要显示出来。
         */
        if (TextUtils.isEmpty(text) || text.equals("未知异常") || text.toString().startsWith("HTTP 504")) {
            return;
        }
        try {
            toast.getView().isShown();
            toast.setText(text);
        } catch (Exception e) {
            toast = Toast.makeText(application, text, toastDuration);
        }
        toast.show();
    }

    public static void show(Application application, CharSequence text) {
        show(application, text, Toast.LENGTH_SHORT);
    }
}

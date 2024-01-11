package com.socket.minamanager.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

class ConnectUtils {
    /**
     * 获取当前时间
     *
     * @return
     */
    static String stringNowTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

}

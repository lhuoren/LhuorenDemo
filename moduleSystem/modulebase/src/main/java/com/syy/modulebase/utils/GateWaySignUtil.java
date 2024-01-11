package com.syy.modulebase.utils;

import com.tencent.mars.xlog.Log;

import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * 网关签名工具
 *
 * @Author: yeyongjian
 * @Date: 2020-11-17 11:04
 */
public class GateWaySignUtil {
    private static Charset UTF8 = Charset.forName("utf-8");
    static String TAG = "GateWaySignUtil";


    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes(UTF8));
            byte byteData[] = md.digest();
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                buffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return buffer.toString();
        } catch (Exception e) {
            Log.e(TAG, "ex={}", e.getMessage(), e);
            return null;
        }
    }


    public static class KeyValueEntity {

        public KeyValueEntity() {
        }

        public KeyValueEntity(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String name;
        public String value;
    }


}

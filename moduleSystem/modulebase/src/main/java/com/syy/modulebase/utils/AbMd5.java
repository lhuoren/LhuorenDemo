package com.syy.modulebase.utils;

import android.util.Log;
import java.security.MessageDigest;

public class AbMd5 {
    public AbMd5() {
    }

    public static final String MD5(String str) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] strTemp = str.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] tmp = mdTemp.digest();
            char[] strs = new char[32];
            int k = 0;

            for(int i = 0; i < 16; ++i) {
                byte byte0 = tmp[i];
                strs[k++] = hexDigits[byte0 >>> 4 & 15];
                strs[k++] = hexDigits[byte0 & 15];
            }

            return (new String(strs)).toUpperCase();
        } catch (Exception var9) {
            return null;
        }
    }

    public static void main(String[] args) {
        Log.d("AbMd5", MD5("2011123456").toLowerCase());
    }
}
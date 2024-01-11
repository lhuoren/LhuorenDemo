
package com.syy.modulebase.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.syy.modulebase.BaseApplication;

public class SharedPrefaceUtils {

    private static String FILE_NAME = "easysmartpatrol";

    /**
     * 保存布尔值的sharedPreference
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }


    public static void saveBoolean(String key, boolean value) {
        SharedPreferences sp = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * 获取布尔值的sharedPreference
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean b) {
        SharedPreferences sp = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, b);
    }

    public static boolean getBoolean(String key) {
        SharedPreferences sp = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    /**
     * 保存字符串的SharedPreference
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    public static void saveString(String key, String value) {
        SharedPreferences sp = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    /**
     * 获取字符串的sharedPreference
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, null);
    }

    public static String getString(String key) {
        SharedPreferences sp = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, null);
    }

    /**
     * 保存数值的SharedPreference
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    public static void saveInt(String key, int value) {
        SharedPreferences sp = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(String key) {
        SharedPreferences sp = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

    public static int getInt(String key, int defVal) {
        SharedPreferences sp = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defVal);
    }

    public static void saveLong(String key, long value) {
        SharedPreferences sp = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putLong(key, value).apply();
    }

    public static long getLong(String key) {
        SharedPreferences sp = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, 0);
    }

    public static long getLong(String key, long defVal) {
        SharedPreferences sp = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defVal);
    }

    public static void saveFloat(String key, float value) {
        SharedPreferences sp = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putFloat(key, value).apply();
    }

    public static float getFloat(String key) {

        SharedPreferences sp = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getFloat(key, 0);
    }

    public static float getFloat(String key, float defVal) {
        SharedPreferences sp = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getFloat(key, defVal);
    }

    public static void remove(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(key);
        edit.apply();
    }

    public static void remove(String key) {
        SharedPreferences sharedPreferences = BaseApplication.getInstanceContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(key);
        edit.apply();
    }

}

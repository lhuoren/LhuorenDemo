package com.syy.modulebase.http.telephony;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.PermissionChecker;

import com.syy.modulebase.utils.AppInfoManager;

import cn.gacnio.it.database.SPUtils;


/**
 * ****************************************************************************
 * Copyright (C) 2018-2023 GAC-NIO Corporation. All rights reserved
 * File        : PhoneUtils.java
 * <p>
 * Description : 为应用，提供telephony框架层模块统一接口封装
 * <p>
 * Creation    : 2018-08-08
 * History     : 2018-08-08, Creation
 * Notes：
 * ****************************************************************************
 */

public class PhoneUtils {
    private static final String TAG = "PhoneUtils";

    private PhoneUtils() {
        Log.w(TAG, "u can't instantiate me...");
    }

    private static final  String UUID_KEY = "UUID_KEY";
    private static String sUUID;
    private static String customerUUID;

    private static TelephonyManager getTelephonyService() {
        Context context = AppInfoManager.getInstance().getContext();

        if (null == context) {
            return null;
        }

        // 获取目标版本
        int targetSdkVersion = Build.VERSION.SDK_INT;
        try {
            final PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            targetSdkVersion = info.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
        }
        // 检查用户授权
        int checkPermResult = PackageManager.PERMISSION_GRANTED;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (targetSdkVersion >= Build.VERSION_CODES.M) {
                checkPermResult = context.checkSelfPermission("android.permission.READ_PHONE_STATE");
            } else {
                checkPermResult = PermissionChecker.checkSelfPermission(context, "android.permission.READ_PHONE_STATE");
            }
        }

        if (checkPermResult == PackageManager.PERMISSION_DENIED) {
            return null;
        }

        return (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

    }

    /**
     * 判断设备是否是手机
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isPhone() {
        TelephonyManager ts = getTelephonyService();
        return ((null != ts) && (ts.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE));
    }

    public static void phoneStateListener(PhoneStateListener phoneStateCallback) {
        TelephonyManager ts = getTelephonyService();
        if (null != ts) {
            ts.listen(phoneStateCallback, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }



    public static String getStoredUUID() {
        String uuid = SPUtils.getString(UUID_KEY,"");
        if (TextUtils.isEmpty(uuid)) {
            uuid = getUUID();
        }
        return uuid;
    }



    /**
     * 获取IMIE码
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.READ_PHONE_STATE"/>}</p>
     *
     * @return IMIE码
     */
    private static String getUUID() {


        if (!TextUtils.isEmpty(sUUID)) {
            return sUUID;
        }
        try {
            TelephonyManager ts = getTelephonyService();
            if (null != ts) {
                sUUID = ts.getDeviceId();
                if (null == sUUID) {
                    sUUID = getCustomerUUID();
                }
            } else {
                sUUID = getCustomerUUID();
            }
        } catch (Exception e) {
            Log.e(PhoneUtils.class.getSimpleName(), e.getMessage());
            sUUID = getCustomerUUID();

        }

        SPUtils.put(UUID_KEY,sUUID);
        return sUUID;
    }

    private static String getCustomerUUID() {
        if (!TextUtils.isEmpty(customerUUID)) {
            return customerUUID;
        }
        try {
            //一共13位  如果位数不够可以继续添加其他信息
            customerUUID = "" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                    Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
                    Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                    Build.USER.length() % 10;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return customerUUID;
    }


    /**
     * 获取IMSI码
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.READ_PHONE_STATE"/>}</p>
     *
     * @param context 上下文
     * @return IMSI码
     */
    public static String getIMSI(Context context) {
        String imsi = null;
        TelephonyManager ts = getTelephonyService();
        if (null != ts) {
            imsi = ts.getSubscriberId();
        }
        return imsi;
    }

    /**
     * 获取手机状态信息
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.READ_PHONE_STATE"/>}</p>
     *
     * @return DeviceId(IMEI) = 99000311726612<br>
     * DeviceSoftwareVersion = 00<br>
     * Line1Number =<br>
     * NetworkCountryIso = cn<br>
     * NetworkOperator = 46003<br>
     * NetworkOperatorName = 中国电信<br>
     * NetworkType = 6<br>
     * honeType = 2<br>
     * SimCountryIso = cn<br>
     * SimOperator = 46003<br>
     * SimOperatorName = 中国电信<br>
     * SimSerialNumber = 89860315045710604022<br>
     * SimState = 5<br>
     * SubscriberId(IMSI) = 460030419724900<br>
     * VoiceMailNumber = *86<br>
     */
    public static String getPhoneStatus() {
        TelephonyManager tm = getTelephonyService();
        String str = "";
        str += "DeviceId(IMEI) = " + tm.getDeviceId() + "\n";
        str += "DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion() + "\n";
        str += "Line1Number = " + tm.getLine1Number() + "\n";
        str += "NetworkCountryIso = " + tm.getNetworkCountryIso() + "\n";
        str += "NetworkOperator = " + tm.getNetworkOperator() + "\n";
        str += "NetworkOperatorName = " + tm.getNetworkOperatorName() + "\n";
        str += "NetworkType = " + tm.getNetworkType() + "\n";
        str += "honeType = " + tm.getPhoneType() + "\n";
        str += "SimCountryIso = " + tm.getSimCountryIso() + "\n";
        str += "SimOperator = " + tm.getSimOperator() + "\n";
        str += "SimOperatorName = " + tm.getSimOperatorName() + "\n";
        str += "SimSerialNumber = " + tm.getSimSerialNumber() + "\n";
        str += "SimState = " + tm.getSimState() + "\n";
        str += "SubscriberId(IMSI) = " + tm.getSubscriberId() + "\n";
        str += "VoiceMailNumber = " + tm.getVoiceMailNumber() + "\n";
        return str;
    }

    /**
     * 跳至填充好phoneNumber的拨号界面
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     */
    public static void dial(Context context, String phoneNumber) {
        context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
    }

    /**
     * 拨打phoneNumber
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.CALL_PHONE"/>}</p>
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     */
    public static void call(Context context, String phoneNumber) {
        context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber)));
    }


}
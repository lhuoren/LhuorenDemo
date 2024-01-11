package com.gac.nioapp.test.utils;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.gac.nioapp.test.bean.HycanCarItemBean;
import com.syy.modulebase.manager.UserInfoManager;

import cn.gacnio.it.database.SPUtils;


/**
 * @package： com.gacnio.car.carpurchase.util
 * @describe：
 * @author： liming
 * @time： 2020-03-16 13:45
 * @e-mail： liming@gac-nio.com
 */
public class CarSPUtil {

    public static final int DEFAULT_NO_ORDER_CAR = -1;

    public static final String CUR_CAR_ID = "CUR_CAR_ID";
    public static final String CUR_CAR_VIN = "CUR_CAR_VIN";
    public static final String CUR_CAR_ORDER_ID = "CUR_CAR_ORDER_ID";
    public static final String CUR_CAR_WISH_ID = "CUR_CAR_WISH_ID";
    public static final String CUR_ORDER_TYPE = "CUR_ORDER_TYPE";
    public static final String CUR_TYPE = "CUR_TYPE";
    public static final String CUR_CAR = "CUR_CAR";
    public static final String STAFF_TYPE = "STAFF_TYPE";
    public static final String FINANCIAL_SHOW_SWITCH = "FINANCIAL_SHOW_SWITCH";
    public static final String FINANCIAL_SHOW_SWITCH_MSG = "FINANCIAL_SHOW_SWITCH_MSG";

    public static final String YG_CAR = "YG_CAR";

    //缓存车辆背景url
    public static final String CUR_CAR_IMG_BG_RES_URL = "CUR_CAR_IMG_BG_RES_URL";
    public static final String OPERATION_CAR_SIZE = "OPERATION_CAR_SIZE";

    public static final String CUR_CAR_BLE_CONFIG = "CUR_CAR_BLE_CONFIG";


    public static void putCarOrderBaseId(String carOrderBaseId) {
        SPUtils.put(UserInfoManager.getInstance().getUserId() + CUR_CAR, CUR_CAR_ORDER_ID, carOrderBaseId);
    }

    public static String getCarOrderBaseId() {
        return SPUtils.getString(UserInfoManager.getInstance().getUserId() + CUR_CAR, CUR_CAR_ORDER_ID, "");
    }

    public static void putCarWishId(int carWishId) {
        SPUtils.put(UserInfoManager.getInstance().getUserId() + CUR_CAR, CUR_CAR_WISH_ID, carWishId);
    }

    public static void putNoFileNameCarWishId(int carWishId) {
        SPUtils.put(CUR_CAR, CUR_CAR_WISH_ID, carWishId);
    }

    public static int getCarWishId() {
        return SPUtils.getInt(UserInfoManager.getInstance().getUserId() + CUR_CAR, CUR_CAR_WISH_ID, 0);
    }

    public static int getNoFileNameCarWishId() {
        return SPUtils.getInt(CUR_CAR, CUR_CAR_WISH_ID, 0);
    }

    public static void putCarType(int carType) {
        SPUtils.put(UserInfoManager.getInstance().getUserId() + CUR_CAR, CUR_TYPE, carType);
    }

    public static int getCarType() {
        return SPUtils.getInt(UserInfoManager.getInstance().getUserId() + CUR_CAR, CUR_TYPE, 0);
    }

    public static void putStaffType(int carType) {
        SPUtils.put(UserInfoManager.getInstance().getUserId() + CUR_CAR, STAFF_TYPE, carType);
    }

    public static int getFinancialShowSwitch() {
        return SPUtils.getInt(UserInfoManager.getInstance().getUserId() + CUR_CAR, FINANCIAL_SHOW_SWITCH, 0);
    }

    public static void putFinancialShowSwitch(int financialShowSwitch) {
        SPUtils.put(UserInfoManager.getInstance().getUserId() + CUR_CAR, FINANCIAL_SHOW_SWITCH, financialShowSwitch);
    }

    public static String getFinancialShowSwitchMsg() {
        return SPUtils.getString(UserInfoManager.getInstance().getUserId() + CUR_CAR, FINANCIAL_SHOW_SWITCH_MSG, "");
    }

    public static void putFinancialShowSwitchMsg(String financialShowSwitchMsg) {
        SPUtils.put(UserInfoManager.getInstance().getUserId() + CUR_CAR, FINANCIAL_SHOW_SWITCH_MSG, financialShowSwitchMsg);
    }

    public static int getStaffType() {
        return SPUtils.getInt(UserInfoManager.getInstance().getUserId() + CUR_CAR, STAFF_TYPE, 0);
    }

    public static void putCarOrderType(int carOrderType) {
        SPUtils.put(UserInfoManager.getInstance().getUserId() + CUR_CAR, CUR_ORDER_TYPE, carOrderType);
    }

    public static int getCarOrderType() {
        return SPUtils.getInt(UserInfoManager.getInstance().getUserId() + CUR_CAR, CUR_ORDER_TYPE, DEFAULT_NO_ORDER_CAR);
    }

    public static void putCarSize(int carSize) {
        SPUtils.put(UserInfoManager.getInstance().getUserId() + OPERATION_CAR_SIZE, carSize);
    }

    public static int getCarSize() {
        return SPUtils.getInt(UserInfoManager.getInstance().getUserId() + OPERATION_CAR_SIZE, 1);
    }


    public static void putCurVehicleId(String carCtrlCarId) {
        SPUtils.put(UserInfoManager.getInstance().getUserId() + CUR_CAR, CUR_CAR_ID, carCtrlCarId);
    }

    public static void putCarBgImgUrl(String url) {
        SPUtils.put(UserInfoManager.getInstance().getUserId() + CUR_CAR, CUR_CAR_IMG_BG_RES_URL, url);
    }

    public static void putCurVin(String curVin) {
        SPUtils.put(UserInfoManager.getInstance().getUserId() + CUR_CAR, CUR_CAR_VIN, curVin);
    }

    public static String getCurVin() {
        return SPUtils.getString(UserInfoManager.getInstance().getUserId() + CUR_CAR, CUR_CAR_VIN, "");
    }

    public static String getCurCarImgBgResUrl() {
        return SPUtils.getString(UserInfoManager.getInstance().getUserId() + CUR_CAR, CUR_CAR_IMG_BG_RES_URL, "");
    }

    public static String getCurVehicleId() {
        return SPUtils.getString(UserInfoManager.getInstance().getUserId() + CUR_CAR, CUR_CAR_ID, "");
    }

    public static void setDisableBleBanner() {
        SPUtils.put(UserInfoManager.getInstance().getUserId() + CarSPUtil.getCurVehicleId(), CUR_CAR_BLE_CONFIG, false);
    }

    public static boolean isShowBleBanner() {

        return SPUtils.getBoolean(UserInfoManager.getInstance().getUserId() + CarSPUtil.getCurVehicleId(), CUR_CAR_BLE_CONFIG, true);
    }

    //360标记是否下载过图片，每个颜色一个key
    public static void putCarColorDownLoadLocal(int carTypeId, String typeKey, String code, boolean hasDownLoadPic) {
        SPUtils.put(CUR_CAR, carTypeId + "_" + typeKey + "_" + code, hasDownLoadPic);
    }

    public static boolean getCarColorDownLoadLocal(int carTypeId, String typeKey, String code) {
        return SPUtils.getBoolean(CUR_CAR, carTypeId + "_" + typeKey + "_" + code, false);
    }

    public static void saveSelectCar(HycanCarItemBean car) {
        if (!TextUtils.isEmpty(car.getVin())) {
            putCurVehicleId(car.getVin());
        }
        SPUtils.putObject(YG_CAR, car);
    }

    @Nullable
    public static HycanCarItemBean getLastSelectCar() {
        Object obj = SPUtils.getObject(YG_CAR);
        if (obj == null) {
            return null;
        } else {
            return (HycanCarItemBean) obj;
        }
    }
}

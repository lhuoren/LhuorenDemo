package com.gac.nioapp.test.utils;

import android.text.TextUtils;

/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2019-08-29 13:45
 ** @describe
 *******************************/
public class CarConstantUtil {
    public static final String bookAssistancePhone = "4006061600";
    public static final String CAR_MODEL_VOTE_SUCCESS = "car_model_vote_success";
    public static final String GUEST_AUTH = "guest_auth";//访客
    public static final String FAMILY_AUTH = "family_auth";//家人
    public static final int SHADOW_COLOR = 0xff195b4c;
    public static final float SHADOW_COLOR_ALPHA = 0.3f;
    public static final float SHADOW_ELEVATION = 7;//shadowElevation
    public static final float SHADOW_RADIUS = 4;
    public static final float SHADOW_ELEVATION_AMALL = 5;//shadowElevation


    //空调风量
    //空调风量，取值：{ "0", "auto", "l1", "l2", "l3", "l4", "l5", "l6", "l7", "l8" }，对应 { 关闭，自动，Level 1 ~ Level 8}
    public static final String CLOSE = "0";
    public static final String AUTO = "auto";
    public static final String LEVEL_1 = "l1";
    public static final String LEVEL_2 = "l2";
    public static final String LEVEL_3 = "l3";
    public static final String LEVEL_4 = "l4";
    public static final String LEVEL_5 = "l5";
    public static final String LEVEL_6 = "l6";
    public static final String LEVEL_7 = "l7";
    public static final String LEVEL_8 = "l8";

    public static int changeFansLv2Int(String lv) {
        int level = 0;
        if (TextUtils.isEmpty(lv)) {
            return level;
        }

        switch (lv) {
            case LEVEL_1:
                level = 1;
                break;
            case LEVEL_2:
                level = 2;
                break;
            case LEVEL_3:
                level = 3;
                break;
            case LEVEL_4:
                level = 4;
                break;
            case LEVEL_5:
                level = 5;
                break;
            case LEVEL_6:
                level = 6;
                break;
            case LEVEL_7:
                level = 7;
                break;
            case LEVEL_8:
                level = 8;
                break;
            default:
                level = 0;
                break;

        }
        return level;
    }


    //座椅模式
    public static final String SEAT_MODE_HEATING = "heating";//加热
    public static final String SEAT_MODE_COOLING = "hooling";//通风
    public static final String SEAT_MODE_OFF = "off";//关闭
    public static final String SEAT_MODE_AUTO = "auto";//自动


    //主驾座椅通风功能状态 "0": disable, "1": enable, "2": no function
    public static final String SEAT_DISABLE = "0";//disable
    public static final String SEAT_ENABLE = "1";//enable
    public static final String SEAT_NO_SUPPORT = "2";



    public static final String SEAT_STRENGTH_L1 = "l1";//
    public static final String SEAT_STRENGTH_L2 = "l2";
    public static final String SEAT_STRENGTH_L3 = "l3";//
    public static final String SEAT_STRENGTH_L = "l";//

    //seatType - 座椅类型 FL:左前 FR:右前
    public static final String SEAT_TYPE_FL = "FL";//
    public static final String SEAT_TYPE_FR = "FR";//


    public static final int T_HI = 32;
    public static final int T_LO = 18;//当前目标温度 默认18 = LO
    public static final int T_WARM = 28;//当前目标温度 默认28 = 温暖模式
    public static final String TXT_LO = "Lo";
    public static final String TXT_HI = "Hi";
    public static final String TXT_OFF = "OFF";
    public static final String SEAT_OPEN_L = "LLL";//代表体感打开

    public static final int FANS_HI = 7;
    public static final int FANS_LO = 1;

    public final static int DURATION = 600;//seconds 10分钟

    public static String getStrengthLvByIntc(int lv) {
        String level = SEAT_STRENGTH_L1;

        switch (lv) {
            case 1:
                level = SEAT_STRENGTH_L1;
                break;
            case 2:
                level = SEAT_STRENGTH_L2;
                break;
            case 3:
                level = SEAT_STRENGTH_L3;
                break;
            default:
                level = SEAT_STRENGTH_L1;
                break;

        }
        return level;
    }

    public static int changeStrengthLv2Int(String lv) {
        int level = 0;

        switch (lv) {
            case SEAT_STRENGTH_L1:
                level = 1;
                break;
            case SEAT_STRENGTH_L2:
                level = 2;
                break;
            case SEAT_STRENGTH_L3:
                level = 3;
                break;
            default:
                level = 0;
                break;

        }
        return level;
    }


    public static final int FROM_SELECT_CONTROLLABLE_CAR_REQ = 12345;
    public static final int FROM_SELECT_ORDER_CAR_REQ = 54321;
    public static final int REQUEST_ENABLE_BT = 54321;
    public static final int REQUEST_ENABLE_VIRTUAL_KEY = 1023;
    public static final int REQUEST_CODE_OPEN_GPS = 1024;
    public static final String SWITCH_TO_TELEMATICS_PAGE = "TELEMATICS_PAGE";//控车页
    public static final String SWITCH_TO_ORDER_CAR_PAGE = "ORDER_CAR_PAGE";//订单车辆页
    public static final String SWITCH_TO_REFRESH_ORDER_CAR_PAGE = "ORDER_REFRESH_CAR_PAGE";//刷新订单车辆，从上往下取第一个显示
    public static final String SWITCH_TO_HOME_CAR_PAGE = "ORDER_HOME_CAR_PAGE";//切换到home首页
    public static final String NOTIFY_TRANSLATE_BUTTOM_CHANGE = "notigy_translate_button_change";//小丁盲订通知首页按钮变化成立即开启预定

    //事件类型 {svt_event, life_detect_event, collision_event} 对应{防盗报警事件，生命探测事件，碰撞报警事件}
    public static final String SVT_EVTNT = "svt_event";
    public static final String LIFE_DETECT_VEVNT = "life_detect_event";
    public static final String COLLISION_VEVNT = "collision_event";

    public static final String COLOR_PIC_PATH = "color_pic_path";
    //银基车控页
    public static final String SWITCH_TO_TELEMATICS_PAGE_YG = "TELEMATICS_PAGE_YG";//银基

}

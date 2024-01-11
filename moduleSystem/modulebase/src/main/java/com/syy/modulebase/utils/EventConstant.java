package com.syy.modulebase.utils;

/**
 * @Description：描述信息
 * @Author：Sai
 * @Date：2019/3/15 14:10
 */
public class EventConstant {
    /**
     * 获取用户资料成功
     */
    public static final String EVENT_UPDATE_USERINFO = "updateUserInfoEvent";
    /**
     * 退出登录成功
     */

    /**
     * 重新新获取用户信息,仅仅用户消息
     */
    public static final String EVENT_NEED_UPDATE_USER_INFO_ONLY = "event_need_update_user_info_only";


    /**
     * 发现页,显示回到顶部的图标
     */
    public static final String EVENT_MAIN_ACTIVITY_FOUND_TO_TOP_ICON = "event_main_activity_to_top_icon";

    /**
     * 发现页,显示正常图标
     */
    public static final String EVENT_MAIN_ACTIVITY_FOUND_NORMAL = "event_main_activity_found_normal";

    public static final String EVENT_LOGOUT = "logout_success";
    //未登录
    public static final String EVENT_NO_LOGIN = "EVENT_NO_LOGIN";
    public static final String EVENT_KICK = "kick_success";
    /**
     * 更新用户信息成功
     */
    public static final String EVENT_LOAD_USERINFO_SUCCESS = "updateUserInfoEventSuccess";
    /**
     * 发布帖子成功
     */
    public static final String EVENT_POSTRELEASE_SUCCESS = "post_release_Success";

    /**
     * 发布商品评价成功
     */
    public static final String EVENT_ORDER_COMMENT_RELEASE_SUCCESS = "order_comment_release_Success";


    /**
     * 发布活动嗮图成功
     */
    public static final String EVENT_CAMPAIGN_RELEASE_SUCCESS = "campaign_release_Success";
    /**
     * 数字生活包页面关闭，返回刷新选装数据
     */
    public static final String EVENT_NOTIFY_PERSONALIZE = "event_notify_personalize";

    /**
     * 删除帖子成功
     */
    public static final String EVENT_DELETE_RELEASE_SUCCESS = "deleteReleaseEventSuccess";

    /**
     * 删除商品评价成功
     */
    public static final String EVENT_DELETE_ORDER_COMMENT_SUCCESS = "deleteOrderCommentEventSuccess";

    /**
     * token拿到后进行广播
     */
    public static final String EVENT_LOGIN = "loginTokenEvent";
    /**
     * Rn通知查询新春红包
     */
    public static final String EVENT_CHECK_NEW_YEAR = "checkNewYear";
    /**
     * 微信绑定之后，从新获取用户信息
     */
    public static final String EVENT_UPDATE_USER = "event_update_user_info";
    /**
     * 发现页面下拉刷新，通知左上角消息红点更新
     */
    public static final String EVENT_DISCOVER_LIST_REFRESH_PCOMPLETED = "discover_list_refresh_completed";
    /**
     * 答题签到之后要刷新状态
     */
    public static final String QUESTION_ANSWER_EVENT = "after_question_answer";
    /**
     * 加载例题之后发送，通知发现页面左上角图标
     */
    public static final String QUESTION_LOADED_EVENT = "after_question_loaded";

    /**
     * 0元购刷新商品详情页面
     */
    public static final String ZERO_BUY_REFRESH_PRODUCT_DETAIL_EVENT = "zero_buy_refresh_product_detail";
    /**
     * 0元购倒计时结束
     */
    public static final String ZERO_BUY_CTIME_COUNNTDDOWN_END_EVENT = "zero_buy_time_countdown_end";
    /**
     * 更新tab红点
     */
    public static final String EVENT_NOTIFY_TAB_RED_POINT = "event_notify_tab_red_point";
    /**
     * 个性选装通知外部改变
     */
    public static final String EVENT_CAR_PERSONALIZE_CHANGE_SUCCESS = "car_personalize_change_Success";
    /**
     * 个性选装通知外部改变
     */
    public static final String EVENT_CAR_ITEM_CHECK = "event_car_item_check";
    /**
     * 个性选装通知外部改变
     */
    public static final String EVENT_CAR_ITEM_UN_CHECK = "event_car_item_un_check";
    /**
     * 内饰通知外部修改同步选装
     */
    public static final String EVENT_CAR_INNER_NOTIFY = "car_inner_nofity";
    /**
     * 个性选装详情本地点击，通知外部更新状态
     */
    public static final String EVENT_CAR_PERSONALIZE_LOCAL_DETAIL_NOTIFY = "car_personalize_local_detail_notify";

    /**
     * 个性选装详情本地点击，通知外部更新状态
     */
    public static final String EVENT_CAR_PERSONALIZE_LOCAL_DIALOG_DETAIL_NOTIFY = "car_personalize_local_dialog_detail_notify";
    /**
     * 个性选装详情本地点击，通知外部更新状态
     */
    public static final String EVENT_CAR_PERSONALIZE_LOCAL_NORMAL_DETAIL_NOTIFY = "car_personalize_local_normal_detail_notify";
    /**
     * IM登录成功
     */
    public static final String EVENT_IM_LOGIN_SUCCESS = "im_login_success";

    /**
     * token拿到进行同步账号，成功后进行广播
     */
    public static final String EVENT_MEGA_LOGIN_SUCCESS = "mega_login_success";

    /**
     * 通知首页按钮文字刷新
     */
    public static final String NOTIFY_NORMAL_CAR_FRAGMENT_BTN_CHANGE = "notify_noemal_car_fragment_change";
    /**
     * 通知首页信息流更新
     */
    public static final String NOTIFY_NORMAL_CAR_INFORMATION_CHANGE = "notify_normal_car_information_change";

    /**
     * 购买成功
     */
    public static final String BUY_SUCCESS = "BUY_SUCCESS";

    public static final int DEFAULT_TYPE = 0;
    public static final int UGCTYPE = 1;
    public static final int PGCTYPE = 2;
    public static final int CAMPAIGN_TYPE = 3;
    public static final int SPECIAL_ARTICLE_TYPE = 4;
    public static final int QUESTIONNAIRE_TYPE = 5;
    public static final int UGC_HOT_TYPE = 6;
    public static final int PGC_LIVE = 7; //APP直播类型
    public static final int PUGC_TYPE = 8; //PUGC


    //0 ugc 1 pugc
    public static final int UGC_NORMAL = 0; //ugc
    public static final int UGC_PUGC = 1; //Pugc



    //PGC自类型
    public static final int PGC_CHILD_PGC = 0; //PGC
    public static final int PGC_CHILD_PGC_LIVE = 1; //PGC直播
    public static final int PGC_CHILD_PGV = 2; //PGV
    public static final int PGC_CHILD_MINI_LIVE = 3; //小程序直播

    public static final int COMMENT = 4;

    public static final int ALERT_BUTTON_LEFT = 0;
    public static final int ALERT_BUTTON_RIGHT = 1;


    public static final String EVENT_SHARESUCCESS = "ShareSuccess";
    public static final String EVENT_SHARE_SUCCESS_NO_TOAST = "EVENT_SHARE_SUCCESS_NO_TOAST";
    public static final String MAIN_INDEX = "main_index";
    public static final String PRAISECHANGE = "PRAISECHANGE";
    public static final String CHILDCHANGE = "CHILDCHANGE";
    public static final String FOCUS_STATUSS_CHANGE = "focus_status_change";
    public static final String EVENT_ENROLLMENT_SUCCESS = "EnrollmentSuccess";
    public static final String DISCOVER_INDEX = "discover_index";
    public static final String USP_SCROLL_TO_TOP = "usp_scroll_to_top";
    public static final String FOCUS_STATUSS_ORDER_LIST_ITEM = "focus_status_order_list_item";
    public static final String FOCUS_STATUSS_ORDER_LIST_STATUS = "focus_status_order_list_status";
    public static final String CHILD_SHOPCAR_SELECT = "CHILD_SHOPCAR_SELECT";
    public static final String EVENT_PRODUCT_CAR_COUNT_CHANGE = "EVENT_PRODUCT_CAR_COUNT_CHANGE";
    public static final String EVENT_PRODUCT_BUY_SUCCESS = "EVENT_PRODUCT_BUY_SUCCESS";
    public static final String EVENT_PRODUCT_BUY_LIST_ITEM_SUCCESS = "EVENT_PRODUCT_BUY_LIST_ITEM_SUCCESS";
    public static final String EVENT_POST_CLOSE_TRTC_VIDEO = "EVENT_POST_CLOSE_TRTC_VIDEO";
    public static final String EVENT_PRODUCT_BUY_PAY_CANCEL_SUCCESS = "EVENT_PRODUCT_BUY_PAY_CANCEL_SUCCESS";
    public static final String EVENT_REFRESH_MAINTENANCE_ORDER_DETAIL = "EVENT_REFRESH_MAINTENANCE_ORDER_DETAIL";

    public static final int CHANGETYPE_DELETE = -1;
    public static final int CHANGETYPE_PRAISE_CHANGE = 0;
    public static final int CHANGETYPE_COMMENT_CHANGE = 1;
    public static final int CHANGETYPE_CHILDDELETE = -2;
    public static final int CHANGETYPE_CHILDDADD = 2;
    public static final int CHANGETYPE_FOCUS_STATUS_CHANGE_FOR_LIST = 3;

    public static final int TYPE_CAN_FOCUS = 1;
    public static final int TYPE_IS_FOCUSED = 2;
    public static final int TYPE_FOCUS_EACH_OTHER = 3;
    public static final int TYPE_IS_SELF = 4;

    //1--关注 2--取消关注
    public static final int TYPE_ADD_FOCUS = 1;
    public static final int TYPE_CANCLE_FOCUS = 2;

    public static final String INTENTKEY = "intentKey";
    //微信支付
    public static final String EVENT_WX_PAY_SUCCESS = "wx_pay_success";
    public static final String EVENT_WX_PAY_FAILED = "wx_pay_failed";
    public static final String EVENT_WX_PAY_CANCEL = "wx_pay_cancel";
    public static final String ORDER_CANCEL = "order_cancel";
    public static final String ORDER_PAY_CANCEL = "order_pay_cancel";
    public static final String ORDER_ITEM_TIME_COUNTDOWN = "order_item_time_countdown";

    public static final String SPECIAL_HOT_COME_FROM = "come_from_type";
    public static final String COME_FROM_MINE = "MINE";

    public static final int CHOICE_ADDRESS_RPOVINCE_CITY = 2;
    public static final int CHOICE_ADDRESS_SHORT_NAME = 1;
    public static final int CHOICE_ADDRESS_DETAIL_NAME = 0;
    public static final int CHOICE_ADDRESS_CHARGING_SETUP = 3;
    public static final int CHOICE_ADDRESS_DRIVING = 4;
    public static final int CHOICE_CAR_ORDER = 5;
    public static final int CHOICE_LOAN_CITY = 6;
    public static final int CHOICE_BLIND = 7;
    public static final int CHOICE_ADDRESS_CHOOSE_STORE = 8;
    public static final int CHOICE_ADDRESS_4_LEVEL = 9;

    //投票成功通知
    public static final String VOTE_SUCCESS = "vote_success";

    //红包
    public static final String NOTIFY_REDPACK_STUATUS_CHANGE = "notify_redpack_status_change";
    public static final String NOTIFY_SEND_CALL = "send_video_call";

    //手机钥匙界面更新
    public static final String VIRTUAL_KEY_REFRESH = "virtual_key_refresh";
    //手机钥匙被回收
    public static final String VIRTUAL_KEY_TOOKBACK = "virtual_key_tookback";

    //安全卫士是否有警告，用于控制爱车的tab是否显示红点
    public static final String EVENT_HYCAN_SECURITY_WARN = "hycan_security_warn";
    public static final String EVENT_HYCAN_SECURITY_SAFE = "hycan_security_safe";
    public static final String EVENT_REFRESH_HYCAN_SECURITY_COUNT = "event_refresh_hycan_security_count";


    public static final int EVENT_ACTION_FROM_LOTTY = 2;
    public static final int PAST_GOOD_SELECTION = 1;

    public static final String REFRESH_ORDER_DETAIL = "refresh_order_detail";
    public static final String CONNECTIVITY_CHANGE = "CONNECTIVITY_CHANGE";


    /**
     * 收藏或者取消收藏
     */
    public static final String REFRESH_FAVOERITE_LIST = "refresh_myfavorite_list";

    public static final String EVENT_PERSONALITY_SIGN_SUCCESS = "event_personality_sign_success";
    public static final String EVENT_UPDATE_ALL_WIDGETS = "event_update_all_widgets";



    public static final String WIDGET_KEY_ACTION_LOCK = "widget_key_action_lock";
    public static final String WIDGET_KEY_ACTION_AC = "widget_key_action_ac";
    public static final String WIDGET_KEY_ACTION_WINDOW = "widget_key_action_window";
    public static final String WIDGET_KEY_ACTION_SUNROOF = "widget_key_action_sunroof";
    public static final String WIDGET_KEY_ACTION_TAIL = "widget_key_action_tail";
    public static final String WIDGET_KEY_ACTION_POSITION = "widget_key_action_position";
    public static final String WIDGET_KEY_ACTION_CHARGER = "widget_key_action_charger";


    public static final String EVENT_POST_UPDATE_CLOUD_STATUS = "EVENT_POST_UPDATE_CLOUD_STATUS";

    //路由跳转拦截登录成功
    public static final String EVENT_INTERCEPTOR_LOGIN_SUCCESS = "EVENT_INTERCEPTOR_LOGIN_SUCCESS";

    //远程验车完成
    public static final String EVENT_REMOTE_CHECK_SUCCESS_FINISH = "EVENT_REMOTE_CHECK_SUCCESS_FINISH";

    //远程验车完成继续查看图片
    public static final String EVENT_REMOTE_CHECK_SUCCESS_CONTINUE = "EVENT_REMOTE_CHECK_SUCCESS_CONTINUE";

}

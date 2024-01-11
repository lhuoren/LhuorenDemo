package com.gac.nioapp.test.router;

/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2019-07-17 18:25
 ** @describe
 *******************************/
public class RouterConstant {
    /**
     * 路由跳转
     * 原则上，不同模块应该注解到不同的路由地址分组。否则不同的q模块下编译后会生成相同的Arouter$$Group$$GroupName1.java文件，在项目构建安装后，会发生不可预期的路由地址失败问题（如其中一个Arouter$$Group$$GroupName1.java文件中的路由生效，另一个直接路由失败）。
     * <p>
     * 或者出现以下错误
     * Execution failed for task ':app:transformClassesWithJarMergingForDevDebug'.
     * > com.android.build.api.transform.TransformException: java.util.zip.ZipException: duplicate entry: com/alibaba/android/arouter/routes/ARouter$$Group$$trans_second.class
     */

    //main
    public static final String PATH_TO_MAIN = "/main/MainActivity";
    public static final String PATH_TO_SCAN_QRCODE = "/community/QrScanActivity";
    public static final String PATH_TO_VERIFICATION_CODE = "/main/LoginVerificationCodeActivity";


    //消息页面
    public static final String PATH_TO_HOME_MESSAGE_PAGE = "/community/HomeMessageActivity";
    public static final String PATH_TO_SCAN_QRCODE_TEST = "/common/QrScanTestActivity";
    public static final String PATH_TO_LOGIN = "/main/LoginActivity";
    public static final String PATH_TO_LOGIN_FIX = "/main/LoginActivityFix";
    public static final String PATH_TO_USER_INFO_ACT = "/main/UserInfoAct";
    public static final String PATH_TO_USER_ACCOUNT_ACT = "/main/AccountManagerAct";
    public static final String PATH_TO_ADDRESS_MANAGER_ACTIVITY = "/main/AccountManagerActivity";
    public static final String PATH_TO_ACTIVE_LOG_OUT_VERIFY_CODE = "/main/LogOutVerifyCodeActivity";
    //通用地址选择器
    public static final String PATH_TO_ADDRESS_CHOICE = "/main/CityAreaChooseActivity";
    //通用门店选择器
    public static final String PATH_TO_STORES_CHOICE = "/main/StoresChooseActivity";

    public static final String PATH_TO_ADDRESS_EDIT_ACTIVITY = "/main/AddressEditActivity";
    //设置页面
    public static final String PATH_TO_SETTING_ACTIVITY = "/main/SettingActivity";
    //PDF在线查看
    public static final String PATH_TO_PDF_SHOWING_ACTIVITY = "/commonui/PdfShowingActivity";


    /*community 模块*/
    public static final String PATH_TO_WEBVIEW_ACTIVITY = "/community/WebViewActivity";
    public static final String PATH_TO_AGENT_WEBVIEW_ACTIVITY = "/community/AgentWebViewActivity";
    public static final String PATH_TO_H5_ACTIVITY = "/community/CommonH5Activity";
    public static final String PATH_TO_ALBUM_ACTIVITY = "/community/AlbumActivity";
    public static final String PATH_TO_CAR_MODEL_IMAGE_ALBUM_ACTIVITY = "/community/CarModelImageAlbumActivity";
    public static final String PATH_TO_ALBUM_GALLERY_ACTIVITY = "/community/AlbumGalleryActivity";
    public static final String PATH_TO_AT_FRIEND_ACTIVITY = "/community/AtFriendUserListActivity";
    public static final String PATH_TO_TOPIC_LIST_ACTIVITY = "/community/TopicListActivity";
    public static final String PATH_TO_USER_STATUS_ACTIVITY = "/community/UserStatusActivity";
    public static final String PATH_TO_POST_DETAIL = "/community/PostDetailActivity";
    public static final String PATH_TO_CAMPAIGN_DETAIL = "/community/CampaignDetailActivity";
    public static final String PATH_TO_CAMPAIGN_ORDER_DETAIL = "/community/CampaignOrderDetailActivity";
    public static final String PATH_TO_PRODUCT_DETAIL = "/community/ProductDetailActivity";
    public static final String PATH_TO_PRODUCT_ORDER_DETAIL = "/community/OrderDetailActivity";
    public static final String PATH_TO_RED_PAPER = "/community/SurpriseActivity";
    public static final String PATH_TO_NEW_YEAR_RP = "/community/NewYearRPActivity";
    public static final String PATH_TO_EXPERIENCE = "/community/ExperienceActivity";
    public static final String PATH_TO_HYCAN_STAR_RULE = "/community/MyInviteHycanStarRulesActivity";
    public static final String PATH_TO_CAR_MY_INVITE = "/community/MyInviteListActivity";
    public static final String PATH_TO_JINGJIREN_RECORD = "/community/MyInviteRecordActivity";
    public static final String PATH_TO_COMMON_VIDEO = "/community/CommonVideoActivity";
    public static final String PATH_TO_CLOUD_SHOW_ROOM = "/community/CloudShowRoomActivity";
    public static final String PATH_TO_MISSION_CENTER = "/community/MissionCenterActivity";
    public static final String PATH_TO_SIGN_ACTIVITY = "/community/SignInActivity";
    public static final String PATH_TO_QUESTION_MAIN = "/community/QuestionMainActivity";
    public static final String PATH_TO_TREND = "/community/PostReleaseActivity";

    public static final String PATH_TO_UGC_VIDEO_DETAIL = "/community/VideoUgcDetailActivity";
    public static final String PATH_TO_WRITE_TITLE = "/community/NewWriteArticleActivity";
    public static final String PATH_TO_TOPIC_DETAIL = "/community/TopicDetailActivity";
    public static final String PATH_TO_POINT_DETAIL = "/community/PointDetailActivity";
    public static final String PATH_TO_POINT_EX = "/community/PointExchangeActivity";
    public static final String PATH_TO_SPECIAL_HOT = "/community/SpecialHotActivity";

    public static final String PATH_TO_CART_CAR = "/community/ShopCarListActivity";
    public static final String PATH_TO_RAFFLE_ORDER = "/community/RafflePreOrderActivity";
    public static final String PATH_TO_MY_ORDER_COMMENT = "/community/MyOrderCommentActivity";
    public static final String PATH_TO_COMMENT_RELEASE = "/community/CommentReleaseActivity";
    public static final String PATH_TO_ONLY_LANDSCAPE_VIDEO = "/community/OnlyLandscapeVideoActivity";
    public static final String PATH_TO_CLOUD_ROOM_INTEREST_SHORT_VIDEO = "/community/CloudRoomInterestShortVideoActivity";
    public static final String PATH_TO_CLOUD_ROOM_GUIDE_VIDEO = "/community/CloudRoomGuideVideoActivity";

    public static final String PATH_TO_CLASS_ROOM_GUIDE_VIDEO = "/community/ClassRoomGuideVideoActivity";
    public static final String PATH_TO_SHORT_VIDEO = "/community/ShortVideoActivity";
    public static final String PATH_TO_CAR_CLOUD_ROOM_ALBUM = "/community/CloudRoomAlbumActivity";
    public static final String PATH_TO_CAR_MODEL_VIDEO = "/community/CarModelVideoActivity";
    public static final String PATH_TO_SEARCH = "/community/SearchMainActivity";


    //hycan模块
    public static final String PATH_TO_GOOD_SELECTION = "/hycan/GoodContentSelectionActivity";
    public static final String PTAH_TO_HYCAN_CENTER = "/hycan/HycanCenterActivity";
    public static final String PATH_TO_CAR_MODEL_MAIN = "/hycan/CarModelMainActivity";
    public static final String PATH_TO_VOTE_MAIN = "/hycan/VoteMainActivity";
    public static final String PATH_TO_HYCAN_IDEA = "/hycan/HycanIdeaActivity";
    public static final String PATH_TO_IDEA_DETAIL = "/hycan/IdeaDetailActivity";
    public static final String PATH_TO_IDEA_LIST = "/hycan/MyCommitIdeaListActivity";

    //小CAN
    public static final String PATH_TO_XIAO_CAN_TEACH_LIST = "/xiaocan/MyTeachListActivity";
    public static final String PATH_TO_XIAOCAN = "/xiaocan/XiaoCanMainActivity";
    //聊天
    public static final String PATH_TO_RED_PACK_SEND = "/chat/RedPackSendActivity";
    public static final String PATH_TO_CHAT_ACTIVITY = "/chat/ChatActivity";
    public static final String PATH_TO_CHAT_SELECT_FRIEND_CHAT = "/chat/SelectFriendChatActivity";
    public static final String PATH_TO_CHAT_SELECT_FRIEND = "/chat/SelectFriendActivity";
    //public static final String PATH_TO_CONVERSATION_ACTIVITY = "/chat/ConversationActivity";

    //公用KEY参数
    public static final String URL = "webview_url";
    public static final String TITLE = "webview_title";
    public static final String WEB_TITLE_FIRST = "webview_title_first";
    public static final String HIDE_TITLEBAR = "hideTitleBar";
    public static final String HIDE_TITLEBAR_TITLE = "hideTitleBarTitle";

    public static final String INTENT_KEY_IMAGES = "images";
    public static final String INTENT_KEY_BIG_IMAGES = "big_images";
    public static final String INTENT_KEY_POSTION = "position";
    public static final String INTENT_URL = "url";
    public static final String CONTENT_TITLE = "contentTitle";
    public static final String IS_HYCAN_LIVE = "is_hycan_live";
    public static final String PLANNER = "PLANNER";
    public static final String AFTER_SALE_CUSTOMER_SERVICE = "AFTER_SALE_CUSTOMER_SERVICE";


    //car module 的放在这里
    public static final String PATH_TO_CAR_SELECTION = "/car/CarSelectionActivity";
    public static final String PATH_TO_CAR_ORDER_DETAIL = "/car/CarOrderDetailActivity";
    public static final String PATH_TO_CAR_G05_ORDER_DETAIL = "/car/CarG05OrderDetailActivity";

    public static final String PATH_TO_CAR_PAY_SUCCEES = "/car/PaySucceesShareActivity";
    public static final String PATH_TO_CAR_DRIVER = "/car/DrivingTestActivity";
    public static final String PATH_TO_CAR_USP_PAGE = "/car/CarUspActivity";
    public static final String PATH_TO_SETUP_CHARGING_INFO = "/car/SetupChargingInfoActivity";
    public static final String PATH_TO_SETUP_CHARGING_STAKE = "/car/SetupChargingStakeActivity";
    public static final String PATH_TO_SETUP_CHARGING_INTRODUCE = "/car/SetupChargingIntroduceActivity";
    public static final String PATH_TO_007_ORDER_PREVIEW = "/car/CarPreOderActivity";
    public static final String PATH_TO_PERSON_DESIGN_INFO = "/car/PersonalitySignActivity";
    public static final String PATH_TO_ERSON_DESIGN_DETAIL = "/car/PersonalityDesignDetailActivity";
    public static final String PATH_TO_CAR_BIG_ORDER = "/car/CarBigOrderDetailActivity";
    public static final String PATH_TO_CAR_Z03_ORDER = "/car/CarZ03OrderDetailActivity";
    public static final String PATH_TO_SETUP_CHARGING_COMMENT = "/car/SetupChargingCommentActivity";
    public static final String PATH_TO_SETUP_CHARGE_ACTION = "/car/ActiveChargeActionActivity";
    public static final String PATH_TO_SETUP_CHARGE_HISTORY = "/car/ActiveChargeHistoryActivity";
    public static final String PATH_TO_CAR_DELIVER_PLANS = "/car/CarDeliverPlansActivity";
    public static final String PATH_TO_CAR_DELIVER_PLANS_FINAL = "/car/CarDeliverFinalPlansActivity";
    public static final String PATH_TO_CAR_AFTER_SALES_STATUS = "/car/AfterSalesStatusActivity";
    public static final String PATH_TO_CAR_AFTER_SALES = "/car/AfterSalesActivity";
    public static final String PATH_TO_POWER_DETAIL = "/car_public/PowerOrderDetailActivity";
    public static final String PATH_TO_CAR_SERVICE_STATION = "/car/ServiceStationActivity";
    public static final String PATH_TO_CAR_POWER_TICKET = "/car_public/PowerTicketActivity";
    public static final String PATH_TO_CAR_MAINTENANCE_TICKET = "/car/MaintenanceTicketActivity";
    public static final String PATH_TO_OFFLINE_CAR_STATION = "/car/OfflineStationActivity";
    public static final String PATH_TO_CHOOSE_MY_STORE = "/car/ChooseMyStoreActivity";
    public static final String PATH_TO_MY_STORE = "/car/MyStoreActivity";
    public static final String PATH_TO_CAR_MAINTENANCE_ORDER_DETAIL = "/car/MaintenanceCarOrderDetailActivity";
    public static final String PATH_TO_BLIND_RUSH_BUY = "/car/BlindRushBuyActivity";
    public static final String PATH_TO_ORDER_IMMEDIATELY = "/car/OrderImmediatelyActivity";
    public static final String PATH_TO_COMMON_WEB = "/car/CommonWebViewActivity";
    public static final String PATH_TO_WEEX_ACTIVITY = "/weex/WeexActivity";
    public static final String PATH_TO_FINANCIAL_PLAN = "/car/CarLoanActivity";
    public static final String PATH_TO_ACTIVE_CHARGE_LAUNCH = "/car/ActiveChargeLaunchActivity";
    public static final String PATH_TO_CAR_USER_MANUAL = "/car/UserManualActivity";


    //car_maintenance module
    public static final String PATH_TO_CAR_MAINTENANCE_CHARGE_MANAGEMENT = "/car_maintenance/CarMaintenanceChargeManagementActivity";
    public static final String PATH_TO_CAR_MAINTENANCE_APPOINTMENTTIME = "/car_maintenance/CarMaintenanceAppointmentTimeActivity";
    public static final String PATH_TO_CAR_MAINTENANCE_MORE = "/car_maintenance/CarMaintenanceMoreActivity";
    public static final String PATH_TO_CAR_MAINTENANCE_SENTINEL_MODEL = "/car_maintenance/CarMaintenanceSentinelModelActivity";
    public static final String PATH_TO_CAR_MAINTENANCE_REAL_TIME_MONITORING = "/car_maintenance/CarMaintenanceRealTimeMonitoringActivity";
    public static final String PATH_TO_CAR_MAINTENANCE_MONITORING_RECORD = "/car_maintenance/CarMaintenanceMonitoringRecordActivity";
    public static final String PATH_TO_CAR_MAINTENANCE_SENTINEL_ALARM = "/car_maintenance/CarMaintenanceSentryAlarmActivity";
    public static final String PATH_TO_CAR_MAINTENANCE_DISPLAY_VIDEO = "/car_maintenance/CarMaintenanceDisplayVideoActivity";
    public static final String PATH_TO_CAR_MAINTENANCE_FAILURE_REASON = "/car_maintenance/CarMaintenanceFailureReasonActivity";
    public static final String PATH_TO_CAR_MAINTENANCE_HANDLE_MACHINE_WALLPAPER = "/car_maintenance/CarMaintenanceHandleMachineWallpaperActivity";

    public static final String PATH_TO_CAR_MAINTENANCE_THERMOSTATIC_COCKPIT = "/car_maintenance/CarMaintenanceThermostaticCockpitActivity";
    public static final String PATH_TO_CAR_MAINTENANCE_BOOK_HEATED_CABIN = "/car_maintenance/CarMaintenanceBookHeatedCabinActivity";

    public static final String PATH_TO_CAR_MAINTENANCE_KL_CHARGE = "/car_maintenance/CarMaintenanceChargeActionActivity";
    public static final String PATH_TO_CAR_MAINTENANCE_AIR_RESERVE_LIST = "/car_maintenance/CarMaintenanceAirReserveListActivity";
    public static final String PATH_TO_CAR_ACTIVSTION = "/car_maintenance/CarActivationActivity";
    public static final String PATH_TO_SOFTWARE_VERSION = "/car_maintenance/CarSoftwareVersionActivity";
    public static final String PATH_TO_CAR_CLOUD_ALBUM = "/car_maintenance/CloudAlbumActivity";
    public static final String PATH_TO_CLOUD_SHARE_PIC = "/car_maintenance/CloudSharePicActivity";
    public static final String PATH_TO_DRIVER_LICENSE = "/car_maintenance/DriverLicenseManagerActivity";
    public static final String PATH_TO_HEALTH_CHARGE = "/car_maintenance/HealthChargeActivity";
    public static final String PATH_TO_HYCAN_SECURITY_LIVE = "/car_maintenance/HycanSecurityLiveActivity";
    public static final String PATH_TO_POWER_ENERGIZE = "/car_maintenance/PowerEnergizeActivity";
    public static final String PATH_TO_CAR_SELECT_CAR = "/car_maintenance/SelectCarActivity";
    public static final String PATH_TO_FOTA_FAIL_NOTICES = "/car_maintenance/VehicleSwUpdateNoticesActivity";
    public static final String PATH_TO_CAR_VIRTUAL_KEY = "/car_maintenance/VirtualKeyManagementActivity";


    //测试路径
    public static final String AROUTE_TEST_PATH = "/test/GacnioTestScheduler";


    public static final String PATH_TO_RN_ACTIVITY = "/reactnative/RnActivity";

    //business
    public static final String PATH_TO_CAR_Z03_SCHEDULE = "/business/CarZ03ScheduleActivity";
    public static final String PATH_TO_Z03_CONFIG_TABLE = "/business/Z03ConfigTableDetailActivity";
    public static final String PATH_TO_RIGHT_ACTIVITY = "/business/RightActivity";
    public static final String PATH_TO_RIGHT_ACTIVITY_007 = "/business/CopyRightActivity";
    public static final String PATH_TO_007_OPTIONAL = "/business/PreviewWishOrderActivity";
    public static final String PATH_TO_CAR_SERIES_ACTIVITY = "/business/CarSeriesZ03DetailsActivity";
    public static final String PATH_TO_CAR_MOD_ACTIVITY = "/business/Z03CarModActivity";
    //订单选配-预定信息界面(A06)
    public static final String PATH_TO_CAR_CONFIG_BOOKING = "/car/CarConfigurationBookingActivity";
    //订单选配-预定信息详情(A06)
    public static final String PATH_TO_CAR_CONFIG_BOOKINGDETAIL = "/car/CarConfigBookingDetailActivity";
    //订单选配-支付成功(A06)
    public static final String PATH_TO_CAR_CONFIG_BOOKING_ORDER_SUCCESS = "/car/CarConfigOrderSuccessActivity";
    //A06大定-预定信息页面
    public static final String PATH_TO_CAR_CONFIG_BIG_BOOK = "/car/CarConfigBigBookActivity";
    //A06大定-订单详情页
    public static final String PATH_TO_CAR_CONFIG_BIG_BOOK_DETAIL = "/car/CarConfigBigBookDetailActivity";
    //合伙人-添加银行卡
    public static final String PATH_TO_BANK_CARD_PHOTO = "/car/BankCardPhotoActivity";
    //合伙人-合同签署
    public static final String PATH_TO_BANK_CARD_SIGN_PREVIEW = "/car/BankCardSignPreviewActivity";
    //售后-线上客服
    public static final String PATH_TO_AFTER_SALE_ONLINE_CUSTOMER_LIST = "/car/OnLineCustomerListActivity";
    //退订-退订账号填写
    public static final String PATH_TO_REFUND_INPUT = "/car/ReFundInputActivity";

    //添加银行卡/实名认证
    public static final String PATH_ADD_BANK_CARD = "/car/AddBankCardActivity";

    public static final String PATH_PDF_WEB_VIEW_ACTIVITY = "/commonui/PdfWebViewActivity";

    //合伙人邀请海报
    public static final String PATH_PARTNER_INVITE_POSTER_ACTIVITY = "/car/PartnerInvitePosterActivity";

    //远程验车
    public static final String PATH_CAR_REMOTE_INSPECTION_ACTIVITY = "/car/CarRemoteInspectionActivity";

}

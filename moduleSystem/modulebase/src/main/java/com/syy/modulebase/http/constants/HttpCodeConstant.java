package com.syy.modulebase.http.constants;

/**
 * @Description：描述信息
 * @Author：Sai
 * @Date：2019/3/20 12:07
 */
public class HttpCodeConstant {
    //拦截器用到
    public static final int HTTPCODE_STATUS_KICK = 678;//单设备互踢，挤下线。
    public static final int HTTPCODE_TOKENEXPIRED = 666;//token过期
    public static final int HTTPCODE_REFRESHTOKENEXPIRED = 667;//token失效，要重新登录

    //注册登录用到
    public static final int HTTPCODE_SENDCODEERROR = 488;//发送手机验证码超过5次
    public static final int HTTPCODE_UNREGISTER = 668;//用户未注册
    public static final int HTTPCODE_CODEERROR = 478;//验证码输入错误

    ////推送消息类型和本地跳转类型/////

    public static final int TYPE_PAGE_CLOSE = -1;//关闭页面

    public static final int TYPE_PAGE_UGC = 1;//跳转到UGC
    public static final int TYPE_PAGE_PGC = 2;//跳转到PGC
    public static final int TYPE_PAGE_CAMPAIGN = 3;//跳转到活动
    public static final int TYPE_PAGE_PRODUCT = 4;//跳转到商品
    public static final int TYPE_PAGE_GONGYI = 5;//跳转到公益活动
    public static final int TYPE_PAGE_QUESTION = 6;//跳转到答题主页
    public static final int TYPE_PAGE_HTTP = 7;//跳转到外部
    public static final int TYPE_PAGE_TOPIC = 8;//跳转到话题
    public static final int TYPE_PAGE_WELFARE = 9;//跳转到有奖邀请
    public static final int TYPE_PAGE_VOTE = 10;//跳转到投票主页
    public static final int TYPE_PAGE_SELECTION = 11;//跳转到甄选家
    public static final int TYPE_PAGE_VOTE_MAIN = 12;//跳转到通用投票主页
    public static final int TYPE_PAGE_DAY_SIGN = 15;//跳转到日签打卡
    public static final int TYPE_PAGE_XIAO_MCAN = 16;//跳转到小CAN训练师

    public static final int TYPE_PAGE_HYCAN_PARTNER = 17;//跳转到内容合伙人
    public static final int TYPE_PAGE_HYCAN_IDEA = 18;//跳转到合创IDEa
    public static final int TYPE_PAGE_CAR_ORDER_DETAIL = 19;//跳转到车订单详情
    public static final int TYPE_PAGE_IDEA_DETAIL = 20;//跳转到合创IDEA详情
    public static final int TYPE_PAGE_IDEA_LIST = 21;//跳转到合创IDEA列表
    public static final int TYPE_PAGE_XIAO_CAN_TEACH_LIST = 22;//跳转到小CAN调教列表
    public static final int TYPE_PAGE_CAMPAIGN_ORDER_DRTAIL = 23;//跳转到活动报名订单详情
    public static final int TYPE_PAGE_PERSONALITY_SIGN = 24;//签名版定制页
    public static final int TYPE_PAGE_CHARGE = 25;//我的专属桩
    public static final int VIRTUAL_KEY_MANAGEMNET = 26;//手机钥匙管理界面
    public static final int TYPE_PAGE_BIG_CAR_ORDER_DETAIL = 27;//大定订单详情

    public static final int TYPE_PAGE_DRIVE_TEST = 29;//预约试驾页面
    public static final int TYPE_PAGE_CAR_CONFIG = 30;//配置表页面
    public static final int TYPE_PAGE_DRIVE_ASSESS = 31;//试驾评价消息跳转
    public static final int TYPE_PAGE_CHARGE_ASSESS = 32;//专属桩安装服务评价邀请
    public static final int TYPE_FOTA_RECEIVE = 33;//FOTA更新
    public static final int TYPE_PAGE_CHARGING_ACTIVE = 34; //充电桩
    public static final int TYPE_PAGE_MY_INVITE = 36; //我的邀请
    public static final int TYPE_PAGE_CLOUD_SHOW_ROOM = 37; //云展厅
    public static final int TYPE_PAGE_CAR_DELIVER = 38; //交付中心
    public static final int TYPE_PAGE_MINI_PROGRAM_STUDIO = 39; //小程序直播间
    public static final int TYPE_PAGE_MISSION_CENTER = 40; //任务中心
    public static final int TYPE_PAGE_MISSION_CENTER_NEW = 105; //新任务中心
    public static final int TYPE_PAGE_CAR_USP = 41; //购车usp
    public static final int TYPE_PAGE_MAIN_SPECIAL_HOT = 42; //专题页
    public static final int TYPE_PAGE_MAIN_MALL_ORDER_DETAIL = 43; //泛产品订单详情
    public static final int TYPE_PAGE_HYCAN_SECURITY_GUARD = 50; //安全卫士
    //底部tab
    public static final int TYPE_PAGE_MAIN = 98;// 首页
    public static final int TYPE_PAGE_HYCAN = 99;//合创中心
    public static final int TYPE_PAGE_CAR_PURCHSE = 14;//跳转到购车tab
    public static final int TYPE_PAGE_MALL_MAIN = 100;//跳转到商城首页
    public static final int TYPE_PAGE_MINE = 102;//跳转到我的
    public static final int TYPE_PAGE_AUTHORIZEDTOACCEPT = 103;//跳转到 授权用车人消息展示页
    public static final int TYPE_PAGE_BIND_WECHAT = 107;//跳转到绑定微信
    public static final int TYPE_PAGE_EDIT_INFORMATION = 106;//跳转到编辑资料
    public static final int TYPE_PAGE_MY_RELEASE = 109;//跳转到我的动态发布页
    public static final int TYPE_PAGE_MY_RELEASE_LIST = 110;//跳转到我的发布列表


    public static final int TYPE_PAGE_INTEGRAL_LDETAIL = 101;//跳转到积分明细
    public static final int TYPE_PAGE_SELECT_FRIEND_CHAT = 9901; //内部自定义跳转
    public static final int TYPE_PAGE_UGC_HOT = 44; //UGC热文
    public static final int TYPE_PAGE_PGC_LIVE = 45; //app直播
    public static final int TYPE_PAGE_PGC_PGV = 46; //pgv
    public static final int TYPE_PAGE_PGC_PUGC = 47; //PUGC
    public static final int TYPE_PAGE_PGC_SPECIAL_ARTICLE = 48; //专题详情
    public static final int TYPE_PAGE_AFTER_SALES = 49; //售后维保
    public static final int TYPE_PAGE_POWER_DETAIL = 51; //一键加电订单详情
    public static final int TYPE_PAGE_REDPAPER = 52; //惊喜红包详情
    public static final int TYPE_PAGE_SERVICE_STATION = 53; //服务网点
    public static final int TYPE_PAGE_POWER_TICKET = 54; //加电券

    public static final int TYPE_PAGE_SHAER_CLOUD_PIC = 55; //云相册分享

    public static final int TYPE_PAGE_CAR_SELECT = 56; //大定选配
    public static final int TYPE_PAGE_MAINTENANCE = 57; //预约维保-取送车详情
    public static final int TYPE_PAGE_PROMOTION_TEMPLE = 58; //促销模版
    public static final int TYPE_TARGET_MINIPROGRAM = 59; // app跳小程序
    public static final int TYPE_PAGE_POWER_ASSESS = 60; // 一键加电评价详情
    public static final int TYPE_PAGE_USER_EXPERIENCE = 61; // 用户体验计划
    public static final int TYPE_PAGE_CERT_LIST = 62; // 数字证件包
    public static final int TYPE_PAGE_MY_TEST_DRIVE_INVITE = 63; //我的邀请试驾
    public static final int TYPE_PAGE_NEW_YEAR_RP = 64; //新年红包

    public static final int TYPE_PAGE_MAINTENANCE_TICKET = 67; //我的取送券
    public static final int TYPE_PAGE_REACT_NATIVE = 75; //去通用的RN页面
    public static final int TYPE_PAGE_HW_CONFIRM = 77; //确认订单
    public static final int TYPE_PAGE_HW_ORDER = 78; //订单详情
    public static final int TYPE_PAGE_APPLETS_ANY = 108; //任意小程序
    public static final int TYPE_PAGE_Z03_DETAILS = 111; //新车详情Z03
    public static final int TYPE_PAGE_Z03_RIGHTS_AND_INTERESTS = 112; //新车权益Z03
    public static final int TYPE_PAGE_CAR_MANUAL = 113; //用车说明书
    public static final int TYPE_PAGE_PDF_BROWSER = 114; //PDF专用WebView

    public static final int TYPE_PAGE_REACT_NATIVE_TRAN = 175; //透明的RN页面

    // TODO: LiuZhiQiang 2022/3/2 首页个人tab新增
    public static final int TYPE_PAGE_CLOUD_ALBUM = 115;//云相册-云展厅里
    public static final int TYPE_PAGE_FUN_LITTLE_VIDEO = 116;//趣味小视频
    public static final int TYPE_PAGE_PLAYER_LITTLE_VIDEO = 117;//播放小视频
    public static final int TYPE_PAGE_CHARGING_ACTIVATION = 138;//充电桩激活
    public static final int TYPE_PAGE_CHARGING_APPLY = 118;//充电桩申请
    public static final int TYPE_PAGE_SETTING = 119;//设置页面
    public static final int TYPE_PAGE_ONLINE_SERVICE = 120;//在线客服
    public static final int TYPE_PAGE_SCANCODE = 121;//个人页面扫码
    public static final int TYPE_PAGE_PARTNER = 122;//成为合伙人
    public static final int TYPE_PAGE_SEND_UGC = 123;//发布UGC
    public static final int TYPE_PAGE_SEND_PUGC = 124;//发布/编辑 PUGC
    public static final int TYPE_PAGE_ALBUM = 136;//图片浏览器
    public static final int TYPE_PAGE_VIRTUAL_KEY = 137;//虚拟钥匙

    // TODO: LiuZhiQiang 2022/4/7
    public static final int TYPE_CAR_MAINTENANCE_CHARGE_MANAGEMENT = 139;//虚拟钥匙
    public static final int TYPE_CAR_MAINTENANCE_MORE = 140;//虚拟钥匙
    public static final int TYPE_CAR_MAINTENANCE_AIR_RESERVE_LIST = 141;//空调预约

    // TODO: LiuZhiQiang 2022/5/30
    public static final int TYPE_PAGE_COMMUNITY_CUSTOM_TAB = 142;//社区自定义页面
    public static final int TYPE_PAGE_COMMUNITY_CAPTCHA_DETECTION = 143;//验证码检测页面

    // TODO: LiuZhiQiang 2022/11/2
    public static final int TYPE_PAGE_USER_GET_MEDAL = 154;//用户获取勋章
    public static final int TYPE_PAGE_CLASS_ROOM_VIDEO = 158;//学习课堂
    // TODO: 2022/12/2
    public static final int TYPE_PAGE_CAR_MODEL = 155;//车型图库
    public static final int TYPE_PAGE_CAR_MODEL_IMAGE_ALBUM = 156;//车型图库图片查看器
    public static final int TYPE_PAGE_CAR_MODEL_VIDEO_ALBUM = 157;//车型图库图片查看器

    //  heyufei 2022/3/15 增加
    public static final int TYPE_PAGE_007_OPTIONAL = 125;//007 选配预览
    public static final int TYPE_PAGE_007_OWNER_TASK_ONLINE_PICK = 131;//007 车主任务:在线选号
    public static final int TYPE_PAGE_007_OWNER_TASK_INDIVIDUALITY_SIGNATURE = 132;//007 车主任务：个性签名
    public static final int TYPE_PAGE_007_TO_MATCHING = 134;//007重新选配
    public static final int TYPE_PAGE_007_SWEEP_CODE_TO_ACTIVATE = 135;//扫码激活
    public static final int TYPE_PAGE_TO_SWITCH_VEHICLES_ORDERS = 133;//扫码激活
    //  heyufei 2022/6/21 增加
    public static final int TYPE_PAGE_NEW_ELECTRICITY_WAY = 144;//扫码激活

    // liuzhiqiang
    public static final int TYPE_PAGE_VOTING_HOME = 145; //投票活动首页
    public static final int TYPE_PAGE_DETAILS_OF_VOTING_WORKS = 146; //投票活动作品详情

    //heyufei 5/6/21 新增
    public static final int TYPE_PAGE_FINANCIAL_PLAN_TO_INTRODUCE = 72; //金融方案介绍页
    public static final int TYPE_PAGE_OFFLINE_EXPERIENCE_POINTS = 70; //线下体验网点
    public static final int TYPE_PAGE_UGC_TOPIC = 74; //UGC文章专题
    public static final int TYPE_PAGE_PUGC_DETAIL2 = 68; //UGC文章专题
    public static final int TYPE_PAGE_MAKE_AN_APPOINTMENT_TEST_H5 = 69; // 跳转h5通用页面(需要生成海报时才用) type=1 (type为1时固定跳转到h5加电服务介绍)
    public static final int TYPE_PAGE_RESERVATION_INFORMATION = 76;//跳转预定信息页


    // jackchen 2022年03月14日新增
    public static final int TYPE_PAGE_SEARCH = 126;//搜索
    public static final int TYPE_PAGE_RIGHTS_BUY_007 = 127; //购车权益
    public static final int TYPE_PAGE_FINANCIAL_PLAN_YI_LIAN_MENG = 129; //金融方案(新) 一链盟
    public static final int TYPE_PAGE_FINANCIAL_PLAN = 130; //金融方案 (通过,参数判断)

    public static final int TYPE_PAGE_MY_STORE = 1016; //选择门店
    public static final int TYPE_PAGE_MAKEANAPPOINTMENTTOMAINTENANCE = 1017; //预约维保 预约界面
    public static final int TYPE_PAGE_OTA_APPOINTMENT = 148; //ota 预约设置页

    public static final int TYPE_PAGE_HEALTH_CHARGE = 149; //健康充电


    public static final int TYPE_PAGE_USER_STATUS = 1001; //去查看个人详情
    public static final int TYPE_PAGE_CHAT = 1002; //去跳转聊天界面
    public static final int TYPE_PAGE_POINT_EX = 1003; //去跳转积分兑换界面
    public static final int TYPE_PAGE_ORDER_EVALUATION = 1004; //去跳转好物订单评价
    public static final int TYPE_PAGE_007_ORDER_PREVIEW = 1005; //007心愿单预览
    public static final int TYPE_PAGE_CART_CAR = 1006; //去跳转好物订单评价
    public static final int TYPE_PAGE_CHARGING_PILE_INFORMATION = 1007; //去跳转安装充电桩信息页


    public static final int TYPE_PAGE_G05_UPGRADE = 1008; //RN跳转盲定升级抢购页面
    public static final int TYPE_PAGE_PARAMETER_Z03 = 1009; //Z03参数页
    public static final int TYPE_PAGE_RIGHT_PAGE = 1010; //权益页
    public static final int TYPE_PAGE_CAR_SERIES_ACTIVITY = 1011; //车系页
    public static final int TYPE_PAGE_CAR_DETAIL = 1012; //详情页
    public static final int TYPE_PAGE_GO_PARKING = 1013; //去泊车
    public static final int TYPE_PAGE_RESELECT_CONFIG = 1014; //Z03 大定重新选配

    public static final int TYPE_PAGE_RN2NATIVE_SPA = 1015; //Rn跳原生 SPA泊车


    public static final int TYPE_PAGE_CAR_CONTROL_PAGE = 2001; //车控页
    public static final int TYPE_PAGE_CAR_ORDER_PAGE = 2002; //订单页


    public static final int TYPE_BUY_SUCCESS = 9999; //去跳转积分兑换界面


    public static final int TYPE_G06_BOOKING_ORDER_DETAIL = 1020; //预定信息界面
    public static final int TYPE_G06_BOOKING_INFO = 1021; //预定信息详情
    public static final int TYPE_NEW_CAR_DETAIL = 1022; //新车详情
    public static final int TYPE_G06_BIG_BOOK = 1023; //大定预定信息
    public static final int TYPE_G06_BIG_BOOK_DETAIL = 1024; //大定订单详情
    public static final int TYPE_CONFIG_CAR_CHANGE_TASK = 1025;//车主任务--充电桩类型（新配置化）
    public static final int TYPE_BANK_CARD_TAKE = 1026;    //合伙人-银行卡拍照
    public static final int TYPE_BANK_CARD_SIGN_PREVIEW = 1027; //合伙人-合同签署
    public static final int TYPE_REFUND_INPUT = 1028;//退订-退订账号填写
    public static final int TYPE_AFTER_SALE_ONLINE_CUSTOMER_LIST = 1029;//售后-线上客服

    public static final int TYPE_HOME_MESSAGE_PAGE = 1030;//聚合消息页面
    //06车控页配置化:我的充电桩
    public static final int TYPE_CONFIGURATION_CHANGE_150 = 150;
    //06车控页配置化:钥匙分享
    public static final int TYPE_CONFIGURATION_CHANGE_151 = 151;
    //06车控页配置化:道路救援
    public static final int TYPE_CONFIGURATION_CHANGE_152 = 152;
    //06车控页配置化:预约维保
    public static final int TYPE_CONFIGURATION_CHANGE_153 = 153;

    //添加银行卡
    public static final int TYPE_PAGE_ADD_BANK_CARD = 1031;
    //修改银行卡
    public static final int TYPE_PAGE_MODIFY_BANK_CARD = 1032;
    //实名认证
    public static final int TYPE_PAGE_ADD_IDENTITY_CARD = 1033;

    public static final int TYPE_PAGE_UGC_VIDEO_DETAIL = 1034; //视频详情页

    public static final int TYPE_PAGE_CDC_SENTINEL_MODEL_PAGE = 1035; //G06哨兵模式主页

    public static final int TYPE_PAGE_CDC_SENTINEL_ALARM_PAGE = 1036; //G06哨兵模式警报页

    public static final int TYPE_PAGE_SPA_PARKING_RECORD_PAGE = 6001; //G06 SPA泊车记录

    public static final int TYPE_PAGE_CAR_DRIVING_WEEKLY = 16005; //驾驶周报

    public static final int TYPE_PAGE_PARTNER_INVITE_POSTER = 9001; //合伙人 邀请海报

    public static final int TYPE_PAGE_CAR_REMOTE_CHECK = 9002; //远程验车

    public static final int TYPE_PAGE_SUPER_PARTNER = 9003;//超伙页面

    //去 车联控制首页
    public static final int TYPE_PAGE_GO_CAR_CONTROL = 6002;
    public static final int TYPE_PAGE_PARTNER_INDEX = 5001;
    public static final int TYPE_PAGE_GO_CHARGERECORD = 6003;

    //去恒温座舱
    public static final int TYPE_PAGE_GO_THERMOSTATIC_COCKPIT = 6004;
}

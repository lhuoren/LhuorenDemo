package com.syy.modulebase.http.constants;

/**
 * @Description：描述信息
 * @Author：Sai
 * @Date：2019/3/11 13:37
 */
public class HttpKeyConstant {
    ////////////从2019.04.28之后分页使用这个
    public static final String CURRENT_PAGE = "current"; //当前页
    public static final String PAGE_SIZE = "size"; //每页大小
    public static final String PAGE_ASC = "isAsc"; //是否倒叙
    ////////////
    public static final String SP_USER_INFO = "sp_user_nfo";
    public static final String PAGE = "page";
    public static final String PAGE_LIMIT = "limit";
    public static final String ID = "id";
    public static final String IS_FIX_LOCAITION = "is_fix_location";
    public static final String PRIZETYPE = "prize_type";
    public static final String SELF = "self";
    public static final String GROUP = "group";
    public static final String GROUP_ID = "group_id";
    public static final String SKU_ID = "skuId";
    public static final String ACTIVITY_PRODUCT_ID = "activityProductId";
    public static final String GROUP_BUYING_ID = "groupId";

    public static final String SHOP_ACTIVITY_TYPE = "activityType"; //活动类型
    public static final String MALL_TYPE = "mall_type";
    public static final String TOPICID = "topicId";
    public static final String ARTICLETOPICID = "articleTopicId";
    public static final String ACTIVITYID = "activityId";
    public static final String IMAGEURL = "imageUrl";
    public static final String ARTICLEID = "articleId";
    public static final String SIGNID = "signId";
    public static final String MOBILE = "mobile";
    public static final String PHONE = "phone";
    public static final String CODE = "code";
    //是否有效
    public static final String IS_EFFECTIVE = "isEffective";
    public static final String SCOPE = "scope";
    public static final String SCOPE_VALUE = "server";//scope对应固定的值
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_BEARER = "Bearer ";
    //Basic Z2FjLWFwcDpnYWN5YW9raiFAIw==   这是旧的2分钟刷新的
    //Basic YXBwLWNsaWVudDphcHBjbGllbnRzZWNyZXQxMjM= 这是新的生产的，13个小时有效期
    public static String AUTHORIZATION_VALUE_DEFAULT = "";//Authorization默认值,已经在gradle里面配置了
    public static final String USERSOURCE = "userSource";
    public static final int USERSOURCE_VALUE = 6;
    public static final String VALIDATECODE = "validateCode";
    public static final String RANDOMSTR = "randomStr";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String GRANT_TYPE = "grant_type";
    public static final String VERSION = "version";
    public static final String APP_TYPE = "app-type";//标识来自不端App, DLM/数字中心
    public static final String DARK_MODE_SIGN = "style-type"; //暗夜版标识, 1:默认。2 暗夜版
    public static final String HYCAN = "hycan";
    public static final String APP_SOURCE = "app-source";
    public static final String CHANNEL_SOURCE = "channel-source";
    public static final String APP_SOURCE_VALUE = "6";
    public static final String STATUS = "status";
    public static final String STATUS_TOP = "0";
    public static final String STATUS_BOTTOM = "1";
    public static final String PRODUCTID = "productId";
    public static final String PROVINCECODE = "provinceCode";
    public static final String IDS = "ids";
    public static final String JWT = "JWT";
    public static final String DATA = "data";
    public static final String PRICE = "price";
    public static final String CONTENT = "content";
    public static final String PARENTCOMMENTID = "parentCommentId";
    public static final String CONTENTID = "contentId";
    public static final String BYREPLYNAME = "byReplyName";
    public static final String CONTENTTYPE = "contentType";
    public static final String IS_PGV = "is_pgv";
    public static final String CONTENT_ITEM_INDEX = "contentItemIndex";
    public static final String CONTENT_SCROLL = "content_scroll";
    public static final String BYREPLYID = "byReplyId";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String WXCODE = "wxCode";
    public static final String USERID = "userId";
    public static final String ACCOUNT = "account";
    public static final String VOTE_ID = "voteId";
    public static final String VISIT_TYPE_FULL = "visitType";
    public static final String VOTE_MANAGE_ID = "voteManageId";
    public static final String USER_NAME = "userName";
    public static final String NAME = "name";
    public static final String MEMBERS = "members";
    public static final String POSTION = "position";
    public static final String PARENT_POSTION = "parent_position";
    public static final String SYSUSERID = "sysUserId";
    public static final String DEVICEBRAND = "device-brand";
    public static final String DEVICEID = "device-id";
    public static final String DURINGTIME = "duringTime";//倒计时秒数
    public static final String LUCK_DRAW_TASK_ID = "luckDrawTaskId";//任务id
    public static final String AUTHORIZATION_VALUE_THIRD = "Basic Z2FjLXRlc3Q6Z2FjLXRlc3Q=";//第三方登录 Basic Z2FjLXRlc3Q6Z2FjLXRlc3Q=
    public static final int USERSOURCE_WECHAT = 3;
    public static final int USERSOURCE_KEY2LAND = 10;//一键登录
    public static final String SYSTEMTAG_UMPS = "upms";//上传图片_用户权限中心
    public static final String SYSTEMTAG_COMMUNITY = "community";//上传图片_社区
    public static final String UGCVOBODY = "ugcVoBODY";//发布帖子的内容

    public static final String FILE = "file";
    public static final String PROVINCEID = "provinceId";
    public static final String CITYID = "cityId";
    public static final String DISTRICTID = "districtId";
    public static final String STREETID = "streetId";
    public static final String PROVINCENAME = "provinceName";
    public static final String CITYNAME = "cityName";
    public static final String DISTRICTNAME = "districtName";
    public static final String STREETNAME = "streetName";
    public static final String RECEIVERNAME = "receiverName";
    public static final String RECEIVERMOBILENO = "receiverMobileNo";
    public static final int ADDRESSDEFAULT = 0;
    public static final int ADDRESSNOTDEFAULT = 1;
    public static final String DETAILADDRESS = "detailAddress";
    public static final String DEFAULTADDRESS = "defaultAddress";
    public static final String RECEIVERZIPCODE = "receiverZipCode";
    public static final String RECEIVERPHONENO = "receiverPhoneNo";
    public static final String MODE = "mode";
    public static final String ADDRESSID = "addressId";
    public static final String ADDORDERITEMS = "addOrderItems";
    public static final String ITEMID = "itemId";
    public static final String ORDER_ITEM_ID = "orderItemId";
    public static final int ORDERSTATUS_SHIPPED = 5; //5=已发货
    public static final int ORDERSTATUS_HAS_PAY = 3; //3=已付款
    public static final int ORDERSTATUS_COMPLETED = 6; //6=已收货
    public static final int ORDERSTATUS_NOT_PAY = 1; //1=未付款
    public static final int ORDERSTATUS_CANCEL = 8; //8=交易取消
    public static final int ORDERSTATUS_CLOSE = 12; //12=交易关闭
    public static final int ORDERSTATUS_SEND_READY = 13; //13=返货准备中
    public static final int ORDERSTATUS_SERVICE_ING = 14; //14=服务进行中

    public static final String URL = "url";
    public static final int ACTIONTYPE_DEFAULT = 1;
    public static final int UNPRAISE = 2;
    public static final int TYPE_COMMENTPRAISE = 10;
    public static final int TYPE_POSTPRAISE = 2;
    public static final int TYPE_SHARE = 3;
    public static final String TYPE = "type";
    public static final String CHILD_TYPE = "child_type";
    public static final String CONTENT_ID = "contentId";
    public static final String ACTIONTYPE = "actionType";
    public static final String PUBLISHID = "publishId";
    public static final String SOURCE = "source";

    public static final String SCENE = "scene";
    public static final String PAY_ID = "payId";
    public static final String KEY = "key";
    public static final String MIN_USER_ID = "minUserId";
    public static final String SHARE_LINK = "share_link";
    public static final String IM_GROUP_ID = "groupId";
    public static final String IM_TO_USER_ID = "toUserId";
    public static final String IM_TO_USER_IM_FLAG = "toUserIdAccount";
    public static final String SHARE_TO_TEST_DRIVER_INVITE = "pages/my-package/my-invite-trail/my-invite-trail";
    public static final String SHARE_TO_MINI_PRO_PAGE = "pages/presale-index/presale-index";
    public static final String SHARE_TO_INVITE_SHARE_PAGE = "pages/my-car/my-car";

    //举报的type参数，用于区分是UGC还是评论
    public static final int REPORTTYPE_UGC = 1;
    public static final int REPORTTYPE_COMMENT = 2;

    public static final String CONTENTTITLE = "contentTitle";
    public static final String IS_HYCAN_LIVE = "is_hycan_live";
    //广告类型
    public static final int ADTYPE_PRODUCT = 1;
    public static final int ADTYPE_PGC = 2;
    public static final int ADTYPE_UGC = 3;
    public static final int ADTYPE_TOPIC = 4;
    public static final String SHAREURL_DEFAULLT = "https://www.gac-nio.com/";//默认的分享地址

    public static final int OFFLINETYPE_HIDE = 6;//1-发布(上架) 2-下架 3-重新上架 4-不显示 5-待审核 6-违规

    //UGC广告类型
    public static final int UGCADTYPE_UGC = 1;
    public static final int UGCADTYPE_PGC = 2;
    public static final int UGCADTYPE_TOPIC = 3;
    //账号绑定
    public static final String BIND_ACCOUNT_WEINXIN = "weixin";
    public static final String BIND_ACCOUNT_WEIBO = "weibo";
    //app更新
    public static final String APP_CAN_UPDATE = "app_can_update";

    public static final String ISPRAISE = "isPraise";
    public static final String PRAISECOUNT = "praiseCount";
    //订单类型
    public static final String ORDER_TYPE = "addOrderType"; // （1:整车订单,2:商城订单,3:服务订单）

    //分享
    public static final String QUESTION_MARK = "?";
    public static final String AND_SIGN = "&";
    public static final String EQUAL_SIGN = "=";

    public static final String S_SHARE_TYPE = "t";
    public static final String S_VISIT_TYPE = "v";
    public static final String S_SHARE_ID = "s";
    public static final String S_USER_UID = "u";
    public static final String S_VOTE_MANAGER_ID = "m";
    public static final String S_HDS = "hd";
    public static final String S_ID = "i";


    //活动类型
    public static final int GROUP_BUYING = 7;  //拼团
    public static final int SPIKES = 5;  //秒杀
    public static final int DISCOUNT = 3;  //折扣
    public static final int FREE_PURCHASE = 2;  //免单活动
    public static final int ZERO_PURCHASE = 1;  //0元购
    public static final int NORMAL_PURCHASE = -1;  //正常购买类型

    public static final int USER_TYPE_MD = 2;  //2:盲订订单商品
    public static final int USER_TYPE_CS = 1;  //1:创世订单商品
    public static final int USER_TYPE_NORMAL = 0;  //0:普通商品

    //支付
    public static final int WX_PAY = 2;
    public static final int ALI_PAY = 3;
    public static final int YXJ_PAY = 1;
    public static final int DJ_PAY = 4;
    public static final int BANK_PAY = 5;


    public static final String PAY_TYPE = "online_pay_type";
    //跳转
    public static final int LOGIN_REQUIRE_CODE = 100;
    public static final String LOCAL_NAV_TYPE = "local_nav_type";

    public static final String ACTION_FROM = "action_from";
    public static final String ACTION_TO = "action_to";
    public static final String MSG = "msg";
    public static final String SHOW_TOPBAR = "show_topbar";
    public static final String CLOSE_IMME = "close_imme";
    public static final String BACK_TXT = "go_back_txt";

    public static final String SESSION_ID = "session_id";
    public static final String SHOW_GUIDE = "show_guide";
    public static final int TYPE_ASK = 1;
    public static final int TYPE_ANSWER = 2;
    public static final int TYPE_ANSWER_LOADING = 3;
    public static final int TYPE_QUESTION_LOADING = 4;

    //第一次
    public static final int TYPE_FIRST_XIAOCAN_POP = 1;
    public static final int TYPE_FIRST_XIAOCAN_GUIDE = 2;
    public static final int TYPE_FIRST_XIAOCAN_TRY_GUIDE = 3;

    //大定tab，分组类型 1=外观 2=内饰 3=个性选装
    public static final int TYPE_OUTER = 1;
    public static final int TYPE_INNER = 2;
    public static final int TYPE_PER = 3;

    //车型类型
    public static final int CAR_07 = 1;
    public static final int CAR_05 = 2;

    //车联网 二维码
    public static final String QR_YTPE = "qr_type";
    public static final String VEHICLE_ID = "vehicle_id";
    public static final String VEHICLE = "vehicle";
    public static final String UUID = "uuid";
    public static final String COUNTRY_CODE = "86";
    //type - 身份证件类型(
    // 二代身份证national，
    // 护照passport，
    // 军官证military，
    // 香港身份证hongkong，
    // 澳门身份证macau，
    // 台胞证taiwan)
    public static final String ID_TYPE = "national";
    public static final String DEFAULT = "default";
    public static final String CLASSIFIER = "gacnio-1";
    //大定
    public static final String WISHID = "wishId";
    public static final String OLDWISHID = "oldWishId";
    public static final String CREATE_WISH_ORDER = "create_wish_order";
    public static final String ORDERID = "orderid";
    public static final String RESELECT = "isReselect"; //预定信息页面,重新选配标识
    public static final String TYPEKEY = "typeKey";
    public static final String TYPECODE = "code";
    public static final String SELECT_STATUS = "selectStatus";
    public static final String ACTION_FROM_DRIVE = "action_from_drive";
    public static final String ORDER_ID = "order_id";
    public static final String KEY_WORD = "keyword";
    public static final String CAR_IMAGE = "carImage";
    public static final String CAR_NAME = "carName";
    public static final String CAR_CONFIG_SERIES_ID = "carConfigSeriesId";
    public static final String CAR_CONFIG_UC_ID = "ucId";


    //切换车型的订单类型 //订单类型：-1：清楚缓存 1-订单，2-心愿单,3-未转化订单
    public static final int CAR_SWITCH_CLEAR_CACHE = -1;
    public static final int CAR_SWITCH_NORMAL_ORDER = 1;
    public static final int CAR_SWITCH_WISH_ORDER = 2;
    public static final int CAR_SWITCH_TRANSLATE_ORDER = 3;


    //金融方案
    public static final int CASE_NORMAL = 3;
    public static final int CASE_WISH = 2;
    public static final int CASE_ORDER = 1;

    //个性化签名
    public static final String SIGN_ID = "SIGN_ID";
    public static final int COME_FROM_MESSAGE = 1;
    public static final String COME_FROM = "COME_FROM";

    public static final String SKIP = "SKIP";

    //分销身份 //0普通人， 1：经纪人，2HYCAN START
    public static final int AGENT_NORMAL = 0;
    public static final int AGENT_JING_JI_REN = 1;
    public static final int AGENT_HYCAN_STAR = 2;


    //商品分类，v3.2.2新增,商品类型：1:实物商品,2:虚拟商品
    public static final int PRODUCT_MATERIAL = 1;
    public static final int PRODUCT_VIRTUAL = 2;
    //v3.2.7.0修改,子级类型：-1:实物默认,0:通用,1:上牌服务,2:一键加电,3:安全卫士,4:云相册,5:场景模式,6:SUV权益包-S包,7:SUV权益包-U包,8:SUV权益包-V包
    public static final int PRODUCT_CHILD_NORMAL = 0;
    public static final int PRODUCT_CHILD_SERVICE = 1;
    public static final int PRODUCT_CHILD_POWER = 2;

    //充电桩评论
    public static final String CHARGING_ID = "CHARGING_ID";
    public static final String CHARG_STATUS = "CHARG_STATUS";

    public static final String IS_HYCAN_STAR = "IS_HYCAN_STAR";

    public static final String USER_AGENT = "user_agent";

    public static final String EVENT_TYPE = "event_type";
    public static final String EVENT_ID = "event_id";
    public static final String MEDIA_URL = "media_url";

    public static final String MODEL = "MODEL";

    public static final String TITLE = "TITLE";
    public static final String NEED_TITLE = "NEED_TITLE";
    public static final String IMAGE_URL = "IMAGE_URL";


    //时间轴状态 状态：1（未进行），2（已进行），3（进行中）
    public static final int TIME_AXIS_PRE_STATUS = 1;
    public static final int TIME_AXIS_LAST_STATUS = 2;
    public static final int TIME_AXIS_CUR_STATUS = 3;

    public static final String INTENT_KEY_COVER = "INTENT_KEY_COVER";
    public static final String INTENT_KEY_ID = "INTENT_KEY_ID";
    public static final String INTENT_KEY_TITLE = "INTENT_KEY_TITLE";
    public static final String INTENT_KEY_CONTENT = "INTENT_KEY_CONTENT";
    public static final String INTENT_KEY_TOPIC_ID = "INTENT_KEY_TOPIC_ID";
    public static final String INTENT_KEY_TOPIC_NAME = "INTENT_KEY_TOPIC_NAME";

    public static final String PAGE_TITLE = "title";
    public static final String CAR_VIN = "vin";
    public static final String EQUITY_ID = "equityId";
    public static final String DEALERCODE = "dealerCode";
    public static final String ASORDERID = "orderId";


    public static final String KEY_BUNDLE_PROJECT = "key_bundle_project";
    public static final String KEY_STORE_ADDR = "storeAddress";
    public static final String KEY_STORE_NAME = "storeName";

    public final static String VIDEO = "video";
    public final static String IMAGE = "image";

    //v3.2.8修改,支付方式：1:积分+现金(默认),2:仅限现金,3:仅限积分,4:最低现金
    public static final int MALL_PAY_NORMAL = 1;
    public static final int MALL_PAY_ONLY_CASH = 2;
    public static final int MALL_PAY_ONLY_SCORE = 3;
    public static final int MALL_PAY_LIMIT_CASH = 4;

    //v3.2.8新增,金额展示方式：0:展示金额,1:不展示金额
    public static final int PRODUCT_SHOW_PRICE = 0;
    public static final int PRODUCT_HIDE_PRICE = 1;


    public final static int DRIVING_CERT = 0x01;
    public final static int DRIVER_CERT = 0x02;
    public final static String CAR_USE_ID = "CAR_USE_ID";

    public static final String PRE_ADDRESS = "preAddress";

    public static final String YEAR_TAG = "tag";
    //北京朝阳合生汇的二维码，用于匹配跳转
    public static final String BEI_JING_HOPSON = "https://qc.hopsontone.com/p/10001?id=218&name=北京朝阳合生汇";

    public static final String IS_MEGA = "IS_MEGA";

    //用于自动跳转积分中心返回码
    public static final int LOGIN_PIONT_CENTER_REQUIRE_CODE = 111;
    //用于自动跳转任务中心返回码
    public static final int LOGIN_TASK_CENTER = 112;

    //用于Okhttp头部缓存设置
    public static final String RESPONSE_CACHE = "HResponseCache-Control";
    //缓存有效期：1d
    public static final long HTTP_CACHE_TIME = 3600L * 24;

    //毫秒
    public static final long HTTP_CACHE_TIME_MILLIS = 1000L * HTTP_CACHE_TIME;

    //银行卡拍摄步骤
    public static final String KEY_STEP = "Step";
    public static final String KEY_FINISH = "finish";
    //预约维保埋点分析
    public static final String KEY_NEED_TO_UPLOAD = "needToUpload";


    public static final String KEY_VIN_WIDGET = "KEY_VIN_WIDGET";

    //使用有效缓存
    public static final String EFFECTIVE_CACHE = "EFFECTIVE_CACHE";
    public static final String KEY_IS_FROM_LOGIN_INTERCEPTOR = "KEY_IS_FROM_LOGIN_INTERCEPTOR";
    public static final String KEY_LOGIN_FROM_PAGE = "KEY_LOGIN_FROM_PAGE";

}

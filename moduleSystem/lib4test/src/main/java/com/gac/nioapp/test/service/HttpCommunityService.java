package com.gac.nioapp.test.service;

import com.gac.nioapp.test.bean.AccountStatusBean;
import com.gac.nioapp.test.bean.ActionResultBean;
import com.gac.nioapp.test.bean.AddressBean;
import com.gac.nioapp.test.bean.BaseItemListBean;
import com.gac.nioapp.test.bean.BaseItemRecordBean;
import com.gac.nioapp.test.bean.BottomTabBean;
import com.gac.nioapp.test.bean.CampaignBean;
import com.gac.nioapp.test.bean.CampaignDetailBean;
import com.gac.nioapp.test.bean.CampaignOrderBean;
import com.gac.nioapp.test.bean.CarHomeIconModel;
import com.gac.nioapp.test.bean.ChannelPersonnelBean;
import com.gac.nioapp.test.bean.CityAreaBean;
import com.gac.nioapp.test.bean.CityAreaLevel4Bean;
import com.gac.nioapp.test.bean.CloudShowStatusBean;
import com.gac.nioapp.test.bean.CollectBean;
import com.gac.nioapp.test.bean.CollectRequestModel;
import com.gac.nioapp.test.bean.CommentBean;
import com.gac.nioapp.test.bean.CommonAlertBean;
import com.gac.nioapp.test.bean.DetailNewYearRp;
import com.gac.nioapp.test.bean.EnrollmentBean;
import com.gac.nioapp.test.bean.ExperienceBean;
import com.gac.nioapp.test.bean.HotSearch;
import com.gac.nioapp.test.bean.IdModel;
import com.gac.nioapp.test.bean.InformationBannerBean;
import com.gac.nioapp.test.bean.IntegralRedEnvelopesForDetailBean;
import com.gac.nioapp.test.bean.IntegralTipsResultBean;
import com.gac.nioapp.test.bean.NewYearIntegralActivitieBean;
import com.gac.nioapp.test.bean.OperationsPopupBean;
import com.gac.nioapp.test.bean.PostBean;
import com.gac.nioapp.test.bean.PugcCarType;
import com.gac.nioapp.test.bean.RedPaperDetail;
import com.gac.nioapp.test.bean.RemoteAlertBean;
import com.gac.nioapp.test.bean.ResponseList;
import com.gac.nioapp.test.bean.SearchActionModel;
import com.gac.nioapp.test.bean.SearchActiveModel;
import com.gac.nioapp.test.bean.SearchArticleModel;
import com.gac.nioapp.test.bean.SearchBean;
import com.gac.nioapp.test.bean.SearchResultModel;
import com.gac.nioapp.test.bean.SearchUserModel;
import com.gac.nioapp.test.bean.ServiceOrderBean;
import com.gac.nioapp.test.bean.SkinModel;
import com.gac.nioapp.test.bean.SpecialArticleDetailBean;
import com.gac.nioapp.test.bean.SplashModel;
import com.gac.nioapp.test.bean.SupportFormat;
import com.gac.nioapp.test.bean.TabRedPointBean;
import com.gac.nioapp.test.bean.TopBannerBean;
import com.gac.nioapp.test.bean.TopEntranceBean;
import com.gac.nioapp.test.bean.TopicBean;
import com.gac.nioapp.test.bean.TopicConfigShowListBean;
import com.gac.nioapp.test.bean.UgcADBean;
import com.gac.nioapp.test.bean.UgcPublishBean;
import com.gac.nioapp.test.bean.UploadPugcRequestModel;
import com.syy.modulebase.http.bean.UserBean;
import com.gac.nioapp.test.bean.UserFollowBean;
import com.gac.nioapp.test.bean.UserIdBean;
import com.gac.nioapp.test.bean.UserSignBean;
import com.gac.nioapp.test.bean.UserStatusBean;
import com.gac.nioapp.test.bean.floatActivityBean;
import com.syy.modulebase.http.bean.HttpResult;
import com.syy.modulebase.http.constants.HttpKeyConstant;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @Description：Community下的请求集
 * @Author：Sai
 * @Date：2019/3/11 11:57
 */
public interface HttpCommunityService {

    /**
     * 发现-推荐 列表查询【无需登录】【完成】
     *
     * @param map
     * @return
     */
    @GET("/community/recommend/front/v3.1.4/list")
    Observable<HttpResult<BaseItemRecordBean<PostBean>>> getRecommendList(@QueryMap Map<String, Object> map);


    @GET("/hycanapp/close-account/simpleAuth/front/ssn/apply/latest")
    Observable<HttpResult<AccountStatusBean>> getAccountStatus();


    @GET("/community/ugc/front/v2.2/getUgcList")
        //获取ugc不带热文接口
    Observable<HttpResult<BaseItemRecordBean<PostBean>>> getUgcList(@QueryMap Map<String, Object> map);

    @GET("/community/ugc/noAuth/front/v3.1.2/getUgcHotList")
        //获取ugc带热文接口
    Observable<HttpResult<BaseItemRecordBean<PostBean>>> getUgcHotList(@QueryMap Map<String, Object> map);

    //车主
    @GET("/community/ugc/noAuth/front/sn/v3.2.6/getCarUserUgcList")
    Observable<HttpResult<BaseItemRecordBean<PostBean>>> getCarOwnerUgcHotList(@QueryMap Map<String, Object> map);

    @GET("community/activity/activityLoading")
    Observable<HttpResult<BaseItemRecordBean<CampaignBean>>> getCampaignList(@QueryMap Map<String, Object> map);

    @GET("community/topic/getTopicById")
    Observable<HttpResult<TopicBean>> getTopicById(@QueryMap Map<String, Object> map);

    // TODO: LiuZhiQiang 2022/4/6 通知后端倒计时结束
    @POST("marketing/toolAct/simpleAuth/front/ssn/task/submit")
    Observable<HttpResult> getCountdownIsOver(@Body RequestBody map);

    // TODO: heyufei 4/26/21  API更新
    @GET("/community/pgc/front/v2.2/getById")
    Observable<HttpResult<PostBean>> getPgcDetailById(@QueryMap Map<String, Object> map);

    //前端 从专题获取pgc详情
    @GET("/community/pgc/front/v3.1.1/getById")
//需要deviceId，需在请求页面添加Manifest.permission.READ_PHONE_STATE动态权限申请
    Observable<HttpResult<PostBean>> getPgcDetailByIdFromSpecial(@QueryMap Map<String, Object> map);

    @GET("/community/ugc/front/v2.2/getUgcById")
    Observable<HttpResult<PostBean>> getUgcDetailById(@QueryMap Map<String, Object> map);

    @GET("/community/ugc/front/v3.1.1/getUgcById")
//需要deviceId，需在请求页面添加Manifest.permission.READ_PHONE_STATE动态权限申请
    Observable<HttpResult<PostBean>> getUgcDetailByIdFromHot(@QueryMap Map<String, Object> map);

    @GET("community/comment/multilevelComment")
    Observable<HttpResult<BaseItemRecordBean<CommentBean>>> getCommentsByPostId(@QueryMap Map<String, Object> map);

    @GET("/community/pugcUgc/simpleAuth/front/v3.2.2/checkCarUserStatus")
    Observable<HttpResult<PugcCarType>> checkCarUserStatus();

    /**
     * 发送注销短信
     */

    @POST("/hycanapp/close-account/simpleAuth/front/ssn/verify-code")
    Observable<HttpResult<Boolean>> getLogOutSmsCode();

    /**
     * 检测注销验证码
     */
    @POST("/hycanapp/close-account/simpleAuth/front/ssn/check-verify-code")
    Observable<HttpResult<Boolean>> getCheckVerifyCode(@Body RequestBody map);


    /**
     * 前端 三级评论加载 (不需要登录)
     *
     * @param map
     * @return
     */
    @GET("community/comment/getCommentByParentCommentId")
    Observable<HttpResult<BaseItemRecordBean<CommentBean>>> getReCommentsByCommentId(@QueryMap Map<String, Object> map);

    @Headers(HttpKeyConstant.HEADER_CONTENT_TYPE + ":" + HttpKeyConstant.APPLICATION_JSON)
    @POST("community/comment/notdec")
    Observable<HttpResult<CommentBean>> comment(@Body RequestBody map);

    @GET("community/sysShipping/findSysShippingByUserId")
    Observable<HttpResult<List<AddressBean>>> getAddressList();

    @GET("community/topic/listTopic")
    Observable<HttpResult<List<TopicBean>>> geTopicList();

    @GET("/commserv/newAddress/noAuth/front/listByParentId")
    Observable<HttpResult<List<CityAreaLevel4Bean>>> getArea4Level(@Query("parentId") long id);

    @POST("commserv/address/get4City")
    Observable<HttpResult<List<CityAreaBean>>> get4City();

    @GET("/order/bigCarOrder/simpleAuth/front/v3.2.0/getLoanCityList")
    Observable<HttpResult<List<CityAreaBean>>> getLoanCityList();

    @GET("/power/carChargingOrder/noAuth/front/v3.2.0/get4City")
    Observable<HttpResult<List<CityAreaBean>>> get4CityCharging();

    @POST("commserv/address/get2City")
    Observable<HttpResult<List<CityAreaBean>>> get2City();

    @POST("community/sysShipping/notdec")
    Observable<HttpResult<AddressBean>> updateAddress(@Body RequestBody map);

    @POST("community/sysShipping/putDetailAddressByIdAndUserId")
    Observable<HttpResult> setAddressAsDefault(@QueryMap Map<String, Object> map);

    @POST("community/sysShipping/delShippingById")
    Observable<HttpResult<AddressBean>> deleteAddress(@QueryMap Map<String, Object> map);

    @POST("community/userAction/front/save")
    Observable<HttpResult<ActionResultBean>> userAction(@Body RequestBody map);

    @POST("community/report/add")
    Observable<HttpResult> report(@Body RequestBody map);

    //    @POST("community/ugc/front/updateUgcById")
    @POST("community/ugc/simpleAuth/front/ssn/v3.3.0/updateUgcById")
    Observable<HttpResult> deleteUGC(@Body RequestBody map);

    @GET("community/sysShipping/getSysShippingByUserId")
    Observable<HttpResult<AddressBean>> getAddressDefault();

    /**
     * 首页清除消息显示的红点  社区互动用户处理行为[把用户消息变成已读]
     *
     * @return
     */

    @POST("/community/userAction/front/v2.2/handUserAcion")
    Observable<HttpResult> changeToRead();

    //    @GET("community/banner/front/list")
    @GET("/community/banner/front/v3.1.1/list")
    Observable<HttpResult<List<UgcADBean>>> getUgcADList();

    @GET("/community/communityRecommendTopicConfig/noAuth/front/v3.2.1/topicList")
    Observable<HttpResult<List<TopicBean>>> getUgcTopicList();

    @POST("community/comment/delCommentById")
    Observable<HttpResult> deleteMyComment(@QueryMap Map<String, Object> map);

    /**
     * 前端 通过评论id获取评论详情
     *
     * @param map
     * @return
     */
    @GET("/community/comment/getCommentById")
    Observable<HttpResult<CommentBean>> getCommentById(@QueryMap Map<String, Object> map);

    @GET("/comment/comment/noAuth/front/v3.1.1/getFirstOrderCommentById")
    Observable<HttpResult<CommentBean>> getFirstOrderCommentById(@QueryMap Map<String, Object> map);

    //    @GET("community/activity/activityDetail")
// TODO: LiuZhiQiang 2022/3/21 接口替换
    @GET("hycanapp/activity/noAuth/front/ssn/detail")
    Observable<HttpResult<CampaignDetailBean>> getCampaignDetailById(@QueryMap Map<String, Object> map);

    /**
     * 前端 校验用户身份接口【完成发布】【3.1.5.5】
     * 地址：/community/activitySignUp/front/v3.1.5.5/checkUserType
     * 类型：GET
     * 状态码：200
     * 简介：校验用户身份
     *
     * @param
     * @return
     */
    @GET("/community/activitySignUp/front/v3.1.5.5/checkUserType")
    Observable<HttpResult> checkUserType(@Query("activityId") String activityId);

    @GET("/community/activity/getSignInfoByActivityId")
    Observable<HttpResult<EnrollmentBean>> getSignInfoByActivityId(@QueryMap Map<String, Object> map);

    @GET("/community/activitySignUp/checkUserSignBySignId")
    Observable<HttpResult<UserSignBean>> checkUserSignBySignId(@QueryMap Map<String, Object> map);

    @Headers(HttpKeyConstant.HEADER_CONTENT_TYPE + ":" + HttpKeyConstant.APPLICATION_JSON)
    @POST("/community/activitySignUp/toSignUp")
    Observable<HttpResult> toSignUp(@Body RequestBody map);


    /**
     * 前端 报名提交接口【3.1.5.5】
     * 地址：/community/activitySignUp/front/v3.1.5/toSignUpActivity
     * 类型：POST
     * 状态码：200
     *
     * @param map
     * @return
     */
    @Headers(HttpKeyConstant.HEADER_CONTENT_TYPE + ":" + HttpKeyConstant.APPLICATION_JSON)
    @POST("/community/activitySignUp/front/v3.1.5/toSignUpActivity")
    Observable<HttpResult> toSignUpActivity(@Body RequestBody map);

    @Headers(HttpKeyConstant.HEADER_CONTENT_TYPE + ":" + HttpKeyConstant.APPLICATION_JSON)
    @POST("/community/activitySignUp/activityCancelSign")
    Observable<HttpResult> toCancelSign(@Body RequestBody map);

    @GET("/community/activityCancelRule/checkRuleByActivityId")
    Observable<HttpResult<UserSignBean>> checkRuleByActivityId(@QueryMap Map<String, Object> map);

    @Headers(HttpKeyConstant.HEADER_CONTENT_TYPE + ":" + HttpKeyConstant.APPLICATION_JSON)

//    @POST("/community/ugc/front/v3.1.1/addUgc")
    // TODO: LiuZhiQiang 2022/3/17 3.0升级改版，修改路径，数据模型不变
    @POST("/hycanapp/ugc/simpleAuth/front/ssn/addUgc")
    Observable<HttpResult<IntegralTipsResultBean>> releasePost(@Body RequestBody map);

    @GET("community/activity/activityOrderLoadByUserId")
    Observable<HttpResult<BaseItemRecordBean<CampaignOrderBean>>> activityOrderLoadByUserId(@QueryMap Map<String, Object> map);

    /**
     * 用户 关注/粉丝 列表 【无需登录】
     *
     * @param map
     * @return
     */
    @GET("/community/userFollow/front/v2.2/list")
    Observable<HttpResult<BaseItemRecordBean<UserFollowBean>>> getUserFollowList(@QueryMap Map<String, Object> map);

    /**
     * 获取好友接口
     *
     * @return
     */
    @GET("/community/userFollow/front/v3.1.1/getUsersFriend")
    Observable<HttpResult<List<UserBean>>> getFriendList();


    /**
     * 添加黑名单/从黑名单中取消
     *
     * @param map
     * @return
     */
    @Headers(HttpKeyConstant.HEADER_CONTENT_TYPE + ":" + HttpKeyConstant.APPLICATION_JSON)
    @POST("/community/userBlacklist/front/v2.2/addOrCancel")
    Observable<HttpResult> userBlacklistAddOrCancle(@Body RequestBody map);

    /**
     * 举报
     *
     * @param map
     * @return
     */
    @Headers(HttpKeyConstant.HEADER_CONTENT_TYPE + ":" + HttpKeyConstant.APPLICATION_JSON)
    @POST("/community/report/front/v2.2/addReport")
    Observable<HttpResult> addReport(@Body RequestBody map);

    /**
     * 前端 个人动态页 【无需登录】
     *
     * @param map
     * @return
     */
    @GET("/community/userContentFlow/front/v2.2/list")
    Observable<HttpResult<BaseItemRecordBean<PostBean>>> getUserStatusList(@QueryMap Map<String, Object> map);

    /**
     * 获取个人动态的统计数据 【无需登录】
     *
     * @param map
     * @return
     */
    @Headers(HttpKeyConstant.HEADER_CONTENT_TYPE + ":" + HttpKeyConstant.APPLICATION_JSON)
    @GET("/community/userContentFlow/front/v2.2/statistics")
    Observable<HttpResult<UserStatusBean>> onLoadUserStatus(@QueryMap Map<String, Object> map);

    /**
     * 前端 文章专题详情接口【0】
     *
     * @param map
     * @return
     */
    @GET("/community/articleDetail/front/v3.1.1/getArticleDetailByArticleId")
    Observable<HttpResult<BaseItemRecordBean<PostBean>>> getArticleDetailListByArticleId(@QueryMap Map<String, Object> map);

    //前端获取ugc热文详情 【无需登录】【0】
    @GET("/community/ugcHot/front/v3.1.1/getUgcHotDetail")
//需要deviceId，需在请求页面添加Manifest.permission.READ_PHONE_STATE动态权限申请
    Observable<HttpResult<BaseItemRecordBean<PostBean>>> getUgcHotDetailList();

    //前端 文章专题详情接口【0】
    @GET("/community/articleTopic/front/v3.1.1/getArticleDetailById")
    Observable<HttpResult<SpecialArticleDetailBean>> getArticleDetailHeaderById(@QueryMap Map<String, Object> map);

    /**
     * 前端 获取问卷链接
     */
    @GET("/community/questionnaire/front/v3.1.1/getQuestionnaireAnswerUrl")
    Observable<HttpResult> getQuestionnaireAnswerUrl(@Query("id") int id);

    /**
     * 前端-tab栏红点是否显示-zzh【完成
     */
    @GET("/operation/window/noAuth/front/v3.1.3.5/getTabRedPointDisp")
    Observable<HttpResult<TabRedPointBean>> getTabRedPointDisp();

    /**
     * 前端】 直播详情
     *
     * @param id
     * @return
     */
    @GET("/community/pgcLiveBroadcast/noAuth/front/v3.1.4/getById")
    Observable<HttpResult<PostBean>> getLiveDetailById(@Query(HttpKeyConstant.ID) String id);

    /**
     *
     */
    @GET("/community/online4sshop/noAuth/front/v3.2.1/getPgvContentByPgcId")
    Observable<HttpResult<PostBean>> getPgvDetailByPcgId(@Query("pgcId") String pcgId);

    /**
     * 【前端】 点击直播
     *
     * @param id
     * @return
     */
    @GET("/community/pgcLiveBroadcast/noAuth/front/v3.1.4/click")
    Observable<HttpResult> LivePlayClick(@Query(HttpKeyConstant.ID) int id);


    /**
     * 前端 通过account换userId-材养 【完成】
     * 地址：/community/ugc/simpleAuth/front/v3.1.5/getUserIdByAccount
     * 类型：GET
     */
    @GET("/community/ugc/simpleAuth/front/v3.1.5/getUserIdByAccount")
    Observable<HttpResult<UserIdBean>> getUserIdByAccount(@Query(HttpKeyConstant.ACCOUNT) String account);

    /**
     * 前端 banner轮播接口【0】
     * 地址：/community/newsBannerRecommendConfig/noAuth/front/v3.2.1/bannerShowList
     * 类型：GET
     */
    @GET("/community/newsBannerRecommendConfig/noAuth/front/v3.2.1/bannerShowList")
    Observable<HttpResult<InformationBannerBean>> getInformationBanner();

    /**
     * 前端 专题展示列表接口【0】
     * 地址：/community/newsTopicShowConfig/noAuth/front/v3.2.1/topicConfigShowList
     * 类型：GET
     */
    @GET("/community/newsTopicShowConfig/noAuth/front/v3.2.1/topicConfigShowList")
    Observable<HttpResult<TopicConfigShowListBean>> getTopicConfigShowList(@QueryMap Map<String, Object> map);

    /**
     * 规划师修改上线状态
     */
    @POST("/community/online4sshop/simpleAuth/front/v3.2.1/updatePlannerOnlineStatus")
    Observable<HttpResult<CloudShowStatusBean>> updatePlannerOnlineStatus(@Body IdModel model);

    /**
     * 【前端】查询快捷入口列表
     */
    @GET("/community/quickEntry/noAuth/front/v3.2.1/list")
    Observable<HttpResult<BaseItemListBean<TopEntranceBean>>> getTopEntranceList();

    /**
     * 【前端】前端 获取热门搜索
     */
    @GET("/community/hotSearch/noAuth/front/v3.2.1/getHotSearch")
    Observable<HttpResult<BaseItemRecordBean<HotSearch>>> getHotSearch();

    /**
     * 【前端】前端 搜索pgc
     */
    @GET("/search/pgc/noAuth/front/v3.2.1/searchPgc")
    Observable<HttpResult<ResponseList<SearchBean>>> searchPgc(@QueryMap Map<String, Object> map);

    /**
     * 【前端】前端 点击热文搜索 用于埋点
     */
    @GET("/community/hotSearch/noAuth/front/v3.2.1/clickHotSearch")
    Observable<HttpResult> clickHotSearch(@Query(HttpKeyConstant.ID) int id);

    @GET("/community/online4sshop/noAuth/front/v3.2.1/clickPgvVideo")
    Observable<HttpResult> recordClickPgV(@Query("pgvId") int pgvId);

    @GET("/maintenance/serviceOrderList/simpleAuth/front/v3.2.2/getOrderList")
    Observable<HttpResult<ResponseList<ServiceOrderBean>>> getServiceOrderList(@Query("serviceType") int type, @Query("current") int page, @Query("size") int pageSize);

    @POST("/community/pugcUgc/simpleAuth/front/v3.2.2/createAndUpdatePugc")
    Observable<HttpResult> createAndUpdatePugc(@Body UploadPugcRequestModel map);

    /**
     * 惊喜红包详情
     *
     * @return
     */
    @GET("/community/simpleAuth/front/3.2.3/alert/detail")
    Observable<HttpResult<RedPaperDetail>> surpriseDetail(@Query(HttpKeyConstant.TYPE) int type, @Query(HttpKeyConstant.ID) int id);

    /**
     * 【前端】弹窗接口
     * <p>
     * //1：惊喜红包弹窗
     *
     * @return
     */
    @GET("/community/simpleAuth/front/3.2.3/showAlert")
    Observable<HttpResult<CommonAlertBean>> showRedPaperAlert(@Query(HttpKeyConstant.TYPE) int type);

    /**
     * 【前端】//临时新年红包
     * <p>
     * //1：新年红包弹窗
     *
     * @return
     */
    @GET("/community/newYearRedPackage/simpleAuth/front/ssn/3.2.7.5/showAlert")
    Observable<HttpResult<CommonAlertBean>> showNewYearRP();


    /**
     * //临时新年红包详情
     *
     * @return
     */
    @GET("/community/newYearRedPackage/simpleAuth/front/ssn/3.2.7.5/detail")
    Observable<HttpResult<DetailNewYearRp>> detailNewYearRP(@Query("id") String id);


    /**
     * 【前端】弹窗接口
     *
     * @return
     */
    @GET("/community/homePopup/noAuth/front/v3.2.3/getHomePopup")
    Observable<HttpResult<RemoteAlertBean>> showHomePopup();


    @GET("/hycanapp/message-push-detail/simpleAuth/front/ssn/pop-window")
    Observable<HttpResult<OperationsPopupBean>> showOperationsPopup();

    /**
     * 【前端】获取底部tab栏标题
     *
     * @return
     */
    @Headers({HttpKeyConstant.RESPONSE_CACHE + ": public, max-age=" + HttpKeyConstant.HTTP_CACHE_TIME, HttpKeyConstant.EFFECTIVE_CACHE + ":hycan"})
    @GET("/hycanapp/lowerTab/noAuth/front/ssn/lowerTabBar")
    Observable<HttpResult<List<BottomTabBean>>> showRemoteTabTitles();

    /**
     * 【前端】前端-获取首页弹窗确认
     *
     * @return
     */
    @GET("/community/homePopup/noAuth/front/v3.2.3/homePopupSure")
    Observable<HttpResult> handleHomePopup();

    /**
     * 【前端】文章收藏接口
     *
     * @return
     */
    @POST("/community/articleCollectInfo/simpleAuth/front/v3.2.3/articleCollect")
    Observable<HttpResult> articleCollect(@Body CollectRequestModel map);

    /**
     * 【前端】 查看我的收藏接口
     *
     * @return
     */
    @GET("/community/articleCollectInfo/simpleAuth/front/v3.2.3/articleCollectList")
    Observable<HttpResult<ResponseList<CollectBean>>> articleCollectList(@Query("current") int current, @Query("size") int pageSize);

    @GET("/community/splash/noAuth/front/v3.2.3/showSplash")
    Observable<HttpResult<SplashModel>> getSplashInfo();

    /**
     * 前端 推荐banner接口
     *
     * @return
     */
    @GET("/community/bannerFocusImageConfig/noAuth/front/v3.2.4/focusImageBannerList")
    Observable<HttpResult<List<TopBannerBean>>> getTopBanner();

    /**
     * 前端 活动banner接口
     *
     * @return
     */
    @GET("/community/bannerActivityConfig/noAuth/front/v3.2.4/activityBannerList")
    Observable<HttpResult<floatActivityBean>> activityBannerList();

    /**
     * 前端 中间banner
     *
     * @return
     */
    @GET("/community/bannerActivityConfig/noAuth/front/v3.2.4/activityIconBanner")
    Observable<HttpResult<TopBannerBean>> getHomeBanner();


    /**
     * 底图icon 接口
     */
    @GET("/community/homeImageConfig/noAuth/front/v3.2.4/getHomeImage")
    Observable<HttpResult<CarHomeIconModel>> getCarHomeIconConfig();

    /**
     * 【前端】用户体验计划-任务列表
     *
     * @return
     */
    @GET("/community/ueQuestionnaireProject/simpleAuth/front/sn/v3.2.6/getProjectTaskList")
    Observable<HttpResult<ExperienceBean>> getProjectTaskList();


    @GET("/search/searchContent/noAuth/front/v3.6.0/searchContent")
    Observable<HttpResult<SearchResultModel>> getSearchGeneral(@Query("keyword") String keyword);

    @GET("/search/searchContent/noAuth/front/v3.6.0/searchUgc")
    Observable<HttpResult<ResponseList<SearchActionModel>>> searchAction(@Query("keyword") String keyword, @Query("current") int current, @Query("size") int size);

    @GET("/search/searchContent/noAuth/front/v3.6.0/searchActivity")
    Observable<HttpResult<ResponseList<SearchActiveModel>>> searchActive(@Query("keyword") String keyword, @Query("current") int current, @Query("size") int size);

    @GET("/search/searchContent/noAuth/front/v3.6.0/searchUser")
    Observable<HttpResult<ResponseList<SearchUserModel>>> searchUser(@Query("keyword") String keyword, @Query("current") int current, @Query("size") int size);

    @GET("/search/pgc/noAuth/front/v3.2.1/searchPgc")
    Observable<HttpResult<ResponseList<SearchArticleModel>>> searchArticle(@Query("keyword") String keyword, @Query("current") int current, @Query("size") int size);


    /**
     * 前端 获取黑白模式主题接口
     */
    @GET("/community/skinTheme/noAuth/front/ssn/v3.2.8.1/getSkinTheme")
    Observable<HttpResult<SkinModel>> getSkinTheme();


    /**
     * 【前端】校验渠道团体人员接口
     */
    @GET("/marketing/channelGroupPersonnel/simpleAuth/front/ssn/v3.2.12/channelPersonnelCheckSimple")
    Observable<HttpResult<ChannelPersonnelBean>> channelPersonnelCheckSimple();

    /**
     * 新春积分红包：活动触发条件接口（弹窗）
     */
    @GET("/marketing/redEnvelope/simpleAuth/front/ssn/activity/showAlert")
    Observable<HttpResult<NewYearIntegralActivitieBean>> newYearEventsTrigger();


    /**
     * 新春积分红包：积分红包详情
     */
    @GET("/marketing/redEnvelope/simpleAuth/front/ssn/activity/detail")
    Observable<HttpResult<IntegralRedEnvelopesForDetailBean>> integralRedEnvelopesForDetails(@Query("recordId") String id);

    /**
     * 扫码完成任务
     *
     * @param map
     * @return
     */
    @POST("/hycanapp/toolAct/simpleAuth/front/ssn/task/finish")
    Observable<HttpResult<Boolean>> scanFinishTask(@Body Map<String, String> map);

    /**
     * 核销任务
     *
     * @param map
     * @return
     */
    @POST("/hycanapp/toolAct/simpleAuth/front/ssn/task/written-off")
    Observable<HttpResult<Boolean>> writeOffTask(@Body Map<String, String> map);

    /**
     * 核销奖品
     *
     * @param map
     * @return
     */
    @POST("/hycanapp/toolAct/simpleAuth/front/ssn/prize-record/written-off")
    Observable<HttpResult<Boolean>> writeOffPrize(@Body Map<String, String> map);

    @POST("/hycanapp/ugc/simpleAuth/front/ssn/checkText")
//    @POST("http://yapi.gacnio-inc.com/mock/545/hycanapp/ugc/simpleAuth/front/ssn/checkText")
    Observable<HttpResult> checkTxt(@Body Map<String, String> map);


    /**
     * fileName	string
     * 必须
     * 文件名，必须要带后缀
     * mock: a.mp4
     * <p>
     * fileType	string
     * 必须
     * 文件类型，VIDEO-视频，IMAGE-图片
     * mock: VIDEO-视频
     *
     * @param map
     * @return
     */
    @POST("/hycanapp/file/simpleAuth/front/ssn/support/format")
//    @POST("http://yapi.gacnio-inc.com/mock/545/hycanapp/file/simpleAuth/front/ssh/support/format")
    Observable<HttpResult<SupportFormat>> checkSupportFormat(@Body Map<String, String> map);


    @POST("/hycanapp/ugc/simpleAuth/front/ssn/addUgcVideo")
//    @POST("http://yapi.gacnio-inc.com/mock/545/hycanapp/ugc/simpleAuth/front/ssn/addUgcVideo")
    Observable<HttpResult<IntegralTipsResultBean>> addUgcVideo(@Body UgcPublishBean ugcPublishBean);


    //   /hycanapp/ugc/noAuth/front/ssn/ugcVideo/details/list
//    @GET("http://yapi.gacnio-inc.com/mock/545/hycanapp/ugc/noAuth/front/ssn/ugcVideo/details/list")
    @GET("/hycanapp/ugc/noAuth/front/ssn/ugcVideo/details/list")
    Observable<HttpResult<List<PostBean>>> getVideoDetails(@Query("id") String id);

    @GET("/hycanapp/ugc/noAuth/front/ssn/ugcVideo/details")
    Observable<HttpResult<PostBean>> getVideoDetail(@Query("id") String id);

    @GET("/hycanapp/ugc/noAuth/front/ssn/ugcVideo/details/list")
    Observable<HttpResult<List<PostBean>>> getVideoDetailsLoadMore();

    @GET("/hycanapp/ugc/simpleAuth/front/ssn/ugcVideo/collect/list")
    Observable<HttpResult<List<PostBean>>> getCollectVideoList(
            @Query("id") String id,
            @Query("current") int current,
            @Query("size") int size
    );

    @GET("/hycanapp/ugc/noAuth/front/ssn/ugcVideo/publisher/list")
    Observable<HttpResult<List<PostBean>>> getUserVideoList(
            @Query("id") String id,
            @Query("userId") String userId,
            @Query("current") int current,
            @Query("size") int size
    );

}

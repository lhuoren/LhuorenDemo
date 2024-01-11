package com.gac.nioapp.test.service;

import static com.syy.modulebase.http.constants.HttpKeyConstant.EFFECTIVE_CACHE;
import static com.syy.modulebase.http.constants.HttpKeyConstant.HTTP_CACHE_TIME;
import static com.syy.modulebase.http.constants.HttpKeyConstant.RESPONSE_CACHE;

import com.gac.nioapp.test.bean.AppConfigBean;
import com.gac.nioapp.test.bean.CarColour;
import com.gac.nioapp.test.bean.Mtco;
import com.gac.nioapp.test.bean.SmsBean;
import com.gac.nioapp.test.bean.UpdateBean;
import com.syy.modulebase.http.bean.UserInfoBean;
import com.syy.modulebase.http.bean.UserTokenBean;
import com.gac.nioapp.test.mvpview.QrScanModel;
import com.syy.modulebase.http.bean.HttpResult;
import com.syy.modulebase.http.constants.HttpKeyConstant;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @Description：用户认证相关接口
 * @Author：Sai
 * @Date：2019/3/15 11:36
 */
public interface HttpAuthService {
    /**
     * 通过手机和验证码登录获取token
     *
     * @param map
     * @return
     */
    @POST("auth/mobile/token")
    Observable<UserTokenBean> loginByPhoneCode(@QueryMap Map<String, Object> map);

    /**
     * 获取用户资料
     *
     * @return
     */
    @GET("admin/user/getSpUserInfo")
    Observable<HttpResult<UserInfoBean>> getUserInfo();

    /**
     * 发送短信
     */

    @GET("admin/smsCode/{mobile}")
    Observable<HttpResult> getSmsCode(@Path(HttpKeyConstant.MOBILE) String mobile);

    /**
     * 发送短信
     */

    @POST("/admin/smsLogin/noAuth/front/v3.2.2/check")
    Observable<HttpResult> smsCodeCheck(@Body RequestBody map);

    /**
     * 发送短信
     */

    @GET("/admin/smsLogin/noAuth/front/v3.2.2/smsCode")
    Observable<HttpResult<SmsBean>> getNewSmsCode(@Query(HttpKeyConstant.PHONE) String phone);

    /**
     * 通过refreshtoken刷新token
     */
    @POST("auth/oauth/token")
    Observable<UserTokenBean> refreshToken(@QueryMap Map<String, Object> map);

    /**
     * 通过手机和验证码登录获取token
     */

    @POST("auth/openid/token")
    Observable<UserTokenBean> loginByThirdCode(@QueryMap Map<String, Object> map);

    /**
     * 检查更新
     *
     * @return
     */
    @GET("/commserv/appVersion/getInfo")
    Observable<HttpResult> checkAppUpdate(@QueryMap Map<String, String> map);

    /**
     * 检查更新
     *
     * @return
     */
    @GET("/commserv/appVersionHot/noAuth/front/3.2.3/getHotVersion")
    Observable<HttpResult<UpdateBean>> checkAppHotFixUpdate(@QueryMap Map<String, Object> map);

    /**
     * 极光id注册更新
     *
     * @param map
     * @return
     */
    @POST("/admin/jpushDevice/front/v3.1.1/registration")
    Observable<HttpResult> registration(@Body RequestBody map);

    /**
     * 扫描二维码
     *
     * @param map
     * @return
     */
    @GET("/commserv/qrCode/simpleAuth/front/v3.2.0/getQrTypeByContent")
    Observable<HttpResult<QrScanModel>> queryQrScanType(@QueryMap Map<String, String> map);

    @GET("/commserv/commonOpenStatusConfig/noAuth/front/v3.2.3/selectAllCommonOpenStatusConfig")
    Observable<HttpResult<List<AppConfigBean>>> selectAllCommonOpenStatusConfig();


    @GET("/order/carConfigAtt/noAuth/front/v3.2.5.5/getColorPic")
    Observable<HttpResult<Mtco>> getColorPic();

    @Headers({RESPONSE_CACHE + ": public, max-age=" + HTTP_CACHE_TIME,
            EFFECTIVE_CACHE + ": hycan"})
    @GET("/vcp/vehicleBase/simpleAuth/front/ssn/v3.2.13/getVehicleColour")
    Observable<HttpResult<List<CarColour>>> getVehicleColour();

    /**
     * 下载文件
     */
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);

    /**
     * 极光推送消息点击事件记录
     *
     * @param map
     * @return
     */
    @POST("/hycanapp/message-push-detail/simpleAuth/front/ssn/system/click")
    Observable<HttpResult<Boolean>> messageClickEvent(@Body Map<String, String> map);
}

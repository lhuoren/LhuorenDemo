package com.syy.modulebase.http.interceptor;

import android.text.TextUtils;

import com.syy.modulebase.http.constants.HttpKeyConstant;
import com.syy.modulebase.http.constants.HttpUrlConstant;
import com.syy.modulebase.http.telephony.PhoneUtils;
import com.syy.modulebase.manager.UserInfoManager;
import com.syy.modulebase.utils.AppInfoManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Sai on 2018/3/16.
 */

public class HeadAuthenticator implements Interceptor {

    private static String headerVersion = null;

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(auth(chain.request()));
    }

    public static Request auth(Request request) {
        Request.Builder builder = request.newBuilder();
        //后台统计只需要x.x 的格式就够了，所以这里截取

        if (headerVersion == null) {
            headerVersion = AppInfoManager.versionName;
        }

        String darkMode = AppInfoManager.getInstance().getDarkMode();
        builder
                .header("Accept-Encoding", "")
                .header("Connection", "keep-alive")
                .header("Accept", "*/*")
                .header(HttpKeyConstant.APP_SOURCE, HttpKeyConstant.APP_SOURCE_VALUE)
                .header(HttpKeyConstant.VERSION, headerVersion)
                .header(HttpKeyConstant.APP_TYPE, HttpKeyConstant.HYCAN)
                .header(HttpKeyConstant.DARK_MODE_SIGN, darkMode)
                .header(HttpKeyConstant.CHANNEL_SOURCE, AppInfoManager.getInstance().getChannelSource());

        //如果登录了有token，就插入token
        String url = request.url().toString();

        if (!url.contains("auth/oauth/token")) {//刷新token的时候接口不应该拦旧token
            String token = UserInfoManager.getInstance().getAccessToken();
            if (!TextUtils.isEmpty(token)) {
                token = HttpKeyConstant.AUTHORIZATION_BEARER + token;
                builder.header(HttpKeyConstant.AUTHORIZATION, token);
            }
//            CommonSimpleLog.d("HeadAuthenticator", "accessToken header:" + token + " , url:" + request.url(), "jack");
        }
        //开发测试环境的AUTHORIZATION 不一样
        if (url.contains("auth/mobile/token") || url.contains("auth/oauth/token") || url.contains("auth/openid/token")) {
            builder.header(HttpKeyConstant.AUTHORIZATION, HttpUrlConstant.getAuthBase());
        }

        builder.header(HttpKeyConstant.DEVICEBRAND, AppInfoManager.getInstance().getMobileModel());
        if (PhoneUtils.getStoredUUID() != null) {//请求头需要添加deviceId的时候，请在请求页面添加Manifest.permission.READ_PHONE_STATE动态权限申请
            builder.header(HttpKeyConstant.DEVICEID, PhoneUtils.getStoredUUID());
        }

        return builder.build();
    }
}

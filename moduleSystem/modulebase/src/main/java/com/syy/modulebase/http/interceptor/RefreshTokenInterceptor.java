package com.syy.modulebase.http.interceptor;

import android.util.Log;

import androidx.annotation.NonNull;
import com.gac.common.utils.CommonSimpleLog;
import com.google.gson.Gson;
import com.syy.modulebase.http.bean.HttpResult;
import com.syy.modulebase.http.constants.HttpCodeConstant;
import com.syy.modulebase.http.event.CommonEvent;
import com.syy.modulebase.http.rx.RxBus;
import com.syy.modulebase.manager.UserInfoManager;
import com.syy.modulebase.utils.EventConstant;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class RefreshTokenInterceptor implements Interceptor {

    private static final String TAG = "RefreshTokenInterceptor";


    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        ResponseBody responseBody = response.body();
        String rBody = cloneResponseBodyStr(responseBody);

        Gson gson = new Gson();
        HttpResult httpResult = null;
        try {
            httpResult = gson.fromJson(rBody, HttpResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (httpResult == null) {
            return response;
        }
        int rCode = httpResult.getCode();

        //这里处理一些统一code响应
        switch (rCode) {
            case HttpCodeConstant.HTTPCODE_TOKENEXPIRED:

                //token过期，刷新token
                if (UserInfoManager.getInstance().refreshToken()) {
                    return chain.proceed(recreateRequestWithNewAccessToken(chain));
                }
                return response;
//                break;

            case HttpCodeConstant.HTTPCODE_REFRESHTOKENEXPIRED:
                //refreshtoken过期，退出登录状态
                UserInfoManager.getInstance().logout();
                CommonSimpleLog.i("RefreshTokenInterceptor", "logout:" + request.url(), "jack");
                RxBus.getInstance().post(EventConstant.EVENT_LOGOUT);
                RxBus.getInstance().post(EventConstant.EVENT_NO_LOGIN);
                break;

            case HttpCodeConstant.HTTPCODE_STATUS_KICK:
                //踢出的情况
                UserInfoManager.getInstance().logout();
                RxBus.getInstance().post(new CommonEvent(EventConstant.EVENT_KICK, httpResult.getMsg()));
                break;
            default:
                break;
        }
        return response;
    }

    /**
     * @param responseBody
     * @return
     * @throws IOException
     */
    private String cloneResponseBodyStr(ResponseBody responseBody) throws IOException {
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.getBuffer();

        Charset charset = StandardCharsets.UTF_8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(StandardCharsets.UTF_8);
            } catch (UnsupportedCharsetException e) {
                e.printStackTrace();
            }
        }
        return buffer.clone().readString(charset);
    }

    /**
     * 重新请求原业务请求
     * 并且执行 auth+signature
     * 顺序与
     *
     * @param chain
     * @return
     */
    private Request recreateRequestWithNewAccessToken(Chain chain) {
        Request request = chain.request().newBuilder().build();
        request = HeadAuthenticator.auth(request);//token
        request = SignatureInterceptor.Companion.signature(request);
        CommonSimpleLog.i(TAG, "recreateRequestWithNewAccessToken:" + request.url(), "jack");
        Log.w(TAG, "recreateRequestWithNewAccessToken:" + request.url());
        return request;
    }


}
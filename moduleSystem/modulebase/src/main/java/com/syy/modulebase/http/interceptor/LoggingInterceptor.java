package com.syy.modulebase.http.interceptor;

import com.gac.common.utils.CommonSimpleLog;
import com.orhanobut.logger.Logger;
import com.syy.modulebase.utils.JsonUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by sai on 2018/5/29.
 */

public class LoggingInterceptor implements Interceptor {

    private static final String TAG = "LoggingInterceptor";

    private final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        RequestBody requestBody = request.body();

        String body = null;
        String printBody = null;

        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            body = buffer.readString(charset);

            if (contentType != null && contentType.type() != null && contentType.type().contains("multipart")) {
                printBody = "上传图片body过大，仅仅打印contentType = " + JsonUtil.toJson(contentType);
            } else {
                printBody = body;
            }
        }


        Logger.d("发送请求\nthread:%s\nmethod：%s\nurl：%s\nheaders: %sbody：%s,", Thread.currentThread().getName(),
                request.method(), request.url(), request.headers(), printBody);

        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        String rBody = null;

        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();

        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                e.printStackTrace();
            }
        }
        rBody = buffer.clone().readString(charset);

        Logger.d("收到响应 %s %s %sms\n请求url：%s\n请求body：%s\n响应body：%s",
                response.code(), response.message(), tookMs, response.request().url(), printBody, rBody);

        try {
            if (response.code() != 200) {
                CommonSimpleLog.e(TAG, String.format("intercept code != 200: \n %s \n%s  ",
                        response.request().url(), rBody
                ), "jack");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
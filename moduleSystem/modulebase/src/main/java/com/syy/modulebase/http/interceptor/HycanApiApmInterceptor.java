package com.syy.modulebase.http.interceptor;

import android.os.Looper;
import android.text.TextUtils;

import com.alibaba.android.arouter.BuildConfig;
import com.syy.modulebase.http.exception.HycanApiReportException;
import com.syy.modulebase.utils.HycanBugReport;
import com.syy.modulebase.utils.ToastUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @author： jack
 * @date： 2022/5/25 1:41 下午
 * @description： 监控数据有误并且上报
 */
public class HycanApiApmInterceptor implements Interceptor {

    private final Charset UTF8 = Charset.forName("UTF-8");


    private static Set<ApiMonitor> apiMonitors = new HashSet<>();


    public static void registerApiMonitor(ApiMonitor apiMonitor) {
        apiMonitors.add(apiMonitor);
    }


    public interface ApiMonitor {

        /**
         * @param request
         * @return false to skip
         */
        boolean filter(Request request);

        /**
         * 处理监控
         *
         * @param request
         * @param responseBody
         * @param requestBodyStr
         * @param responseBodyStr
         */
        void monitor(Request request, ResponseBody responseBody, String requestBodyStr, String responseBodyStr);

    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper() ||
                TextUtils.equals("main", Thread.currentThread().getName())) {

            HycanBugReport.report(new HycanApiReportException() {
                @Override
                public String tag() {
                    return "发现在主线程进行网络请求!已上报bugly";
                }

                @Override
                public String api() {
                    return chain.request().url().toString();
                }

                @Override
                public Object requestBody() {
                    return null;
                }

                @Override
                public Object responseBody() {
                    return null;
                }
            });

            if (BuildConfig.DEBUG) {
                ToastUtil.toastShortMessage("发现在主线程进行网络请求!已上报bugly");
                System.exit(0);
                return null;
            }
        }

        Request request = chain.request();
        Response response = chain.proceed(request);


        RequestBody requestBody = request.body();
//        MediaType contentType = requestBody.contentType();
//        if (contentType != null && contentType.type().contains("multipart")) {//不监控 multipart
//            return response;
//        }


        List<ApiMonitor> needApiMonitorList = new ArrayList<>();


        for (ApiMonitor monitor : apiMonitors) {
            try {
                if (monitor.filter(request)) {
                    needApiMonitorList.add(monitor);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (needApiMonitorList.isEmpty()) return response;


        String requestBodyStr = null;
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            requestBodyStr = buffer.readString(charset);
        }


        ResponseBody responseBody = response.body();
        String responseBodyStr = null;
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
        responseBodyStr = buffer.clone().readString(charset);

        if (response.code() != 200) return response;

        for (ApiMonitor monitor : needApiMonitorList) {
            try {
                monitor.monitor(request, responseBody, requestBodyStr, responseBodyStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return response;
    }
}

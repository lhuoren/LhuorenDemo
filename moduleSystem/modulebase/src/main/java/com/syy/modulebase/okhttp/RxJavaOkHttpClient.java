package com.syy.modulebase.okhttp;

import android.os.Environment;

import com.alibaba.android.arouter.BuildConfig;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.syy.modulebase.okhttp.cookie.SimpleCookieJar;
import com.syy.modulebase.okhttp.https.HttpsUtils;
import com.syy.modulebase.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @author qndroid
 * @function 用来发送get, post请求的工具类，包括设置一些请求的共用参数
 */
public class RxJavaOkHttpClient {
    private static final int TIME_OUT = 90;
    private static final int TEST_CONNECT_TIME_OUT = 8;
    private static OkHttpClient mFastOkHttpClient;
    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getInstance() {

        if (mOkHttpClient == null) {
            LogUtils.e("mOkHttpClient", "mOkHttpClient");
            //更加安全
            synchronized (RxJavaOkHttpClient.class) {
                //缓存的地方     mnt/sdcard
                File file = new File(Environment.getExternalStorageDirectory(), "cache11");
                mOkHttpClient = new OkHttpClient()
                        .newBuilder()
                        .addInterceptor(getSignInInterceptor())
                        .cookieJar(new SimpleCookieJar())
                        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)  //设置写入超时时间
                        .readTimeout(TIME_OUT, TimeUnit.SECONDS)   //设置读取超时时间
                        .connectTimeout(TIME_OUT, TimeUnit.SECONDS) //设置连接的超时时间
                        .followRedirects(true)
                        .sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager())
                        .hostnameVerifier(HttpsUtils.getDoNotVerify())
                        .cache(new Cache(file, 200 * 1024 * 1024))
                        //设置okhttp连接池保活时间为1s,默认为5分钟，5分钟内创建超过2000个连接就会OOM
                        .connectionPool(new ConnectionPool(1000, 10, TimeUnit.SECONDS))
                        .build();
            }
        }
        return mOkHttpClient;
    }

    public static OkHttpClient getConnectForFastInstance() {

        if (mFastOkHttpClient == null) {
            LogUtils.e("mOkHttpClient", "mOkHttpClient");
            //更加安全
            synchronized (RxJavaOkHttpClient.class) {
                //缓存的地方     mnt/sdcard
                File file = new File(Environment.getExternalStorageDirectory(), "cache12");
                mFastOkHttpClient = new OkHttpClient()
                        .newBuilder()
                        .addInterceptor(getSignInInterceptor())
                        .cookieJar(new SimpleCookieJar())
                        .writeTimeout(TEST_CONNECT_TIME_OUT, TimeUnit.SECONDS)  //设置写入超时时间
                        .readTimeout(TEST_CONNECT_TIME_OUT, TimeUnit.SECONDS)   //设置读取超时时间
                        .connectTimeout(TEST_CONNECT_TIME_OUT, TimeUnit.SECONDS) //设置连接的超时时间
                        .followRedirects(true)
                        .sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager())
                        .hostnameVerifier(HttpsUtils.getDoNotVerify())
                        .cache(new Cache(file, 200 * 1024 * 1024))
                        //设置okhttp连接池保活时间为1s,默认为5分钟，5分钟内创建超过2000个连接就会OOM
                        .connectionPool(new ConnectionPool(1000, 10, TimeUnit.SECONDS))
                        .build();
            }
        }
        return mFastOkHttpClient;
    }

    public static OkHttpClient getOkHttpClient() {
        return getInstance();
    }

    private static Interceptor getSignInInterceptor() {
        if (BuildConfig.DEBUG) {
            return new LoggingInterceptor();
        } else {
//            return new Interceptor() {
//                @NotNull
//                @Override
//                public Response intercept(@NotNull Chain chain) throws IOException {
//                    Request request = chain.request()
//                            .newBuilder()
//                            .addHeader("User-Agent", "Mobile") // 标明发送本次请求的客户端
//                            .build();
//                    return chain.proceed(request);
//                }
//            };

            return new Interceptor() {
                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("User-Agent", "Mobile") // 标明发送本次请求的客户端
                            .build();

                    Response response = chain.proceed(request);
                    if (!request.url().toString().contains("oauth/captcha")) {
                        String json = response.body().string();

                        if (response.code() != 200
                                || json.equals("")
                                || json.contains("The upstream server is timing out")) {
                            ResponseBody myBody = ResponseBody.create(MediaType.get("text/plain"), json);
                            return response.newBuilder().body(myBody).build();
                        }

                        JsonObject result = JsonParser.parseString(json).getAsJsonObject();

                        if (result.has("error") && result.get("error") != null) {
                            ResponseBody myBody = ResponseBody.create(MediaType.get("text/plain"), json);
                            return response.newBuilder().body(myBody).build();
                        }

                        String resultJson;
                        if (result.has("data")) {
                            resultJson = result.get("data").toString();
                        } else {
                            resultJson = result.toString();
                        }

                        ResponseBody myBody = ResponseBody.create(MediaType.get("text/plain"), resultJson);
                        return response.newBuilder().body(myBody).build();

                    }
                    return response;
                }
            };
        }
    }

    private static class LoggingInterceptor implements Interceptor {
        private final Charset UTF8 = StandardCharsets.UTF_8;

        @NotNull
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            RequestBody requestBody = request.body();

            String body = null;

            if (requestBody != null) {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                body = buffer.readString(charset);
            }

            LogUtils.e("LoggingInterceptor", "发送请求:" +
                    ",method:" + request.method() + ",url:" + request.url());

            LogUtils.e("LoggingInterceptor", "发送请求:" +
                    ",method:" + request.method() + ",url:" + request.url() + ",headers:" + request.headers() + ",sbody:" + body);

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

            LogUtils.e("LoggingInterceptor",
                    "收到响应:" + response.code() + ",请求message:" + response.message() + ",tookMs:" + tookMs + ",请求url:" + response.request().url() + ",请求body:" + body + ",响应body:" + rBody);

            if (!request.url().toString().contains("oauth/captcha")) {
                String json = response.body().string();

                if (response.code() != 200
                        || json.equals("")
                        || json.contains("The upstream server is timing out")) {
                    ResponseBody myBody = ResponseBody.create(MediaType.get("text/plain"), json);
                    return response.newBuilder().body(myBody).build();
                }

                JsonObject result = JsonParser.parseString(json).getAsJsonObject();

                if (result.has("error") && result.get("error") != null) {
                    ResponseBody myBody = ResponseBody.create(MediaType.get("text/plain"), json);
                    return response.newBuilder().body(myBody).build();
                }

                String resultJson;
                if (result.has("data")) {
                    resultJson = result.get("data").toString();
                    LogUtils.e("resultJson", resultJson);
                } else {
                    resultJson = result.toString();
                }

                ResponseBody myBody = ResponseBody.create(MediaType.get("text/plain"), resultJson);
                return response.newBuilder().body(myBody).build();

            }

            return response;
        }
    }

}
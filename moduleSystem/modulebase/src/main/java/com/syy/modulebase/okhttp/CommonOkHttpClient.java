package com.syy.modulebase.okhttp;

import android.os.Environment;

import com.syy.modulebase.okhttp.cookie.SimpleCookieJar;
import com.syy.modulebase.okhttp.https.HttpsUtils;
import com.syy.modulebase.okhttp.listener.DisposeDataHandle;
import com.syy.modulebase.okhttp.response.CommonFileCallback;
import com.syy.modulebase.okhttp.response.CommonJsonCallback;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author qndroid
 * @function 用来发送get, post请求的工具类，包括设置一些请求的共用参数
 */
public class CommonOkHttpClient {
    private static final int TIME_OUT = 90;
    private static OkHttpClient mOkHttpClient;
    private static Call call = null;
//    private static final ExecutorService executorService = Executors.newFixedThreadPool(300);

//    static {
//        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
//        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        });
//
//        /**
//         *  为所有请求添加请求头，看个人需求
//         */
//        okHttpClientBuilder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request()
//                        .newBuilder()
//                        .addHeader("User-Agent", "Mobile") // 标明发送本次请求的客户端
//                        .build();
//                return chain.proceed(request);
//            }
//        });
//        okHttpClientBuilder.cookieJar(new SimpleCookieJar());
//        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
//        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
//        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
//        okHttpClientBuilder.followRedirects(true);
//        /**
//         * trust all the https point
//         */
//        okHttpClientBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
//        mOkHttpClient = okHttpClientBuilder.build();
//    }


    public static OkHttpClient getInstance() {

        if (mOkHttpClient == null) {
            //更加安全
            synchronized (CommonOkHttpClient.class) {
                //缓存的地方     mnt/sdcard
                File file = new File(Environment.getExternalStorageDirectory(), "cache11");
                mOkHttpClient = new OkHttpClient()
                        .newBuilder()
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request()
                                        .newBuilder()
                                        .addHeader("User-Agent", "Mobile") // 标明发送本次请求的客户端
                                        .build();
                                return chain.proceed(request);
                            }
                        })
                        .cookieJar(new SimpleCookieJar())
                        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)  //设置写入超时时间
                        .readTimeout(TIME_OUT, TimeUnit.SECONDS)   //设置读取超时时间
                        .connectTimeout(TIME_OUT, TimeUnit.SECONDS) //设置连接的超时时间
                        .followRedirects(true)
//                        .sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager())
//                        .hostnameVerifier(HttpsUtils.getDoNotVerify())
                        .cache(new Cache(file, 200 * 1024 * 1024))
                        //设置okhttp连接池保活时间为1s,默认为5分钟，5分钟内创建超过2000个连接就会OOM
                        .connectionPool(new ConnectionPool(1000, 10, TimeUnit.SECONDS))
                        .build();
            }
        }
        return mOkHttpClient;
    }

    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

//    /**
//     * 指定cilent信任指定证书
//     *
//     * @param certificates
//     */
//    public static void setCertificates(InputStream... certificates) {
//        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates, null, null)).build();
//    }

    /**
     * 通过构造好的Request,Callback去发送请求
     *
     * @param request
     * @param handle
     */
//    public static Call get(Request request, DisposeDataHandle handle) {
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new CommonJsonCallback(handle));
//        return call;
//    }
//
//    public static Call post(Request request, DisposeDataHandle handle) {
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new CommonJsonCallback(handle));
//        return call;
//    }
//
//    public static Call downloadFile(Request request, DisposeDataHandle handle) {
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new CommonFileCallback(handle));
//        return call;
//    }

    /**
     * 通过构造好的Request,Callback去发送请求
     *
     * @param request
     * @param handle
     */
    public static Call get(Request request, DisposeDataHandle handle) {
        call = getInstance().newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call post(Request request, DisposeDataHandle handle) {
        call = getInstance().newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call downloadFile(Request request, DisposeDataHandle handle) {
        call = getInstance().newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;
    }
}
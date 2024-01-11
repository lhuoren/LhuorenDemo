package com.syy.modulebase.http;

import android.text.TextUtils;
import com.syy.modulebase.http.interceptor.SignatureInterceptor;
import com.gac.common.utils.CommonSimpleLog;
import com.syy.modulebase.http.adapter.BooleanTypeAdapter;
import com.syy.modulebase.http.adapter.NullStringToEmptyAdapterFactory;
import com.syy.modulebase.http.bean.HttpResult;
import com.syy.modulebase.http.convert.GsonConverterFactory;
import com.syy.modulebase.http.interceptor.HeadAuthenticator;
import com.syy.modulebase.http.interceptor.HycanApiApmInterceptor;
import com.syy.modulebase.http.interceptor.LoggingInterceptor;
import com.syy.modulebase.http.interceptor.RefreshTokenInterceptor;
import com.syy.modulebase.http.constants.HttpKeyConstant;
import com.syy.modulebase.http.constants.HttpUrlConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.syy.modulebase.utils.AppInfoManager;
import com.syy.modulebase.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.gacnio.it.database.daohelper.CommonEntityDaoHelper;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Sai on 2018/3/21.
 */

public class HttpServiceGenerator {
    private final String TAG = "HttpServiceGenerator";
    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 30 * 1000;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 30 * 1000;
    //写入超时，单位：毫秒
    public static final int WRITE_TIME_OUT = 30 * 1000;
    public Retrofit retrofit;
    public HashMap<Class, Object> serviceCache = new HashMap<>();
    private OkHttpClient okHttpClient;

    private volatile static HttpServiceGenerator retrofitManager;
//    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

    private static List<Interceptor> interceptors = new ArrayList<>();
    private static List<Interceptor> nwInterceptors = new ArrayList<>();
    private volatile static Gson gson = new Gson();

    public static void setupInterceptor(Interceptor interceptor) {
        setupInterceptor(interceptor, false);
    }

    public static void setupInterceptor(Interceptor interceptor, boolean networkInterceptor) {

        if (networkInterceptor) {
            nwInterceptors.add(interceptor);
        } else {
            interceptors.add(interceptor);
        }
    }


    /*************************缓存设置*********************/

    //构造方法私有
    private HttpServiceGenerator() {

        File cacheFile = new File(AppInfoManager.getInstance().getContext().getCacheDir(), "hycan-http");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.MILLISECONDS);

        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }

        for (Interceptor interceptor : nwInterceptors) {
            builder.addNetworkInterceptor(interceptor);
        }

        //REQUEST头设置只使用缓存，无网络时，有网络时设置只使用网络
        builder.addInterceptor(mRewriteCacheRequestControlInterceptor)
                //RESPONSE设置允许使用缓存，无网络时
                .addNetworkInterceptor(mRewriteCacheResponseControlInterceptor)
                .addInterceptor(new HeadAuthenticator())
                .addInterceptor(new SignatureInterceptor())
                .addInterceptor(new RefreshTokenInterceptor())
                .addNetworkInterceptor(new HycanApiApmInterceptor())
                .addNetworkInterceptor(new LoggingInterceptor())
                .cache(cache);

        okHttpClient = builder.build();
        String baseApiHost = HttpUrlConstant.getAPPURL();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Boolean.class, new BooleanTypeAdapter())
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
                .create();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseApiHost)
                .build();
    }


    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static HttpServiceGenerator create() {
        if (retrofitManager == null) {

            synchronized (HttpServiceGenerator.class) {
                if (retrofitManager == null) {
                    retrofitManager = new HttpServiceGenerator();
                }
            }
        }
        return retrofitManager;
    }

    /**
     * 获取Service
     *
     * @return Service
     */
    public static <T> T create(final Class<T> service) {
        create();
        T cache = (T) retrofitManager.serviceCache.get(service);
        if (cache == null) {
            cache = retrofitManager.retrofit.create(service);
            retrofitManager.serviceCache.put(service, cache);
        }
        return cache;
    }

    /**
     * 请求头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheRequestControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.isConnected(AppInfoManager.getInstance().getContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();

            } else {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_NETWORK)
                        .build();
            }
            boolean needEffectCache = request.header(HttpKeyConstant.EFFECTIVE_CACHE) != null && NetWorkUtil.isConnected(AppInfoManager.getInstance().getContext());
            String params = "";
            if ("POST".equals(request.method())) {
                RequestBody requestBody = request.body();
                if (requestBody != null) {
                    Buffer buffer = new Buffer();
                    requestBody.writeTo(buffer);
                    params = buffer.readUtf8();
                }
            }

            String key = request.url() + request.method() + params;
            try {
                Response originalResponse = chain.proceed(request);
                if (needEffectCache) {
                    ResponseBody body = originalResponse.body();
                    if (body != null) {
                        String bodyString = body.string();
                        try {
                            HttpResult result = gson.fromJson(bodyString, HttpResult.class);
                            if (result.getCode() == 0) {
                                if (!"2233".equals(originalResponse.header("NO-CACHE"))) {
                                    CommonSimpleLog.i(TAG, "保存缓存数据: " + key, "flyme");
                                    CommonEntityDaoHelper.get().saveCommonData(key, "http_cache", new HttpCacheBean(key, bodyString, System.currentTimeMillis()));
                                } else {
                                    CommonSimpleLog.e(TAG, "已经在使用缓存数据,无需再次保存: " + key, "flyme");
                                }
                            } else {
                                HttpCacheBean cache = CommonEntityDaoHelper.get().getCommonData(key, "http_cache", HttpCacheBean.class);
                                if (cache != null && System.currentTimeMillis() - cache.createTime <= HttpKeyConstant.HTTP_CACHE_TIME_MILLIS) {
                                    CommonSimpleLog.i(TAG, "使用有效缓存数据01: " + key, "flyme");
                                    return originalResponse.newBuilder()
                                            .code(200)
                                            .body(ResponseBody.create(MediaType.parse("application/json;charset=UTF-8"), cache.response))
                                            .build();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            HttpCacheBean cache = CommonEntityDaoHelper.get().getCommonData(key, "http_cache", HttpCacheBean.class);
                            if (cache != null && System.currentTimeMillis() - cache.createTime <= HttpKeyConstant.HTTP_CACHE_TIME_MILLIS) {
                                CommonSimpleLog.i(TAG, "使用有效缓存数据02: " + key, "flyme");
                                return originalResponse.newBuilder()
                                        .code(200)
                                        .body(ResponseBody.create(MediaType.parse("application/json;charset=UTF-8"), cache.response))
                                        .build();
                            }
                        }
                        return originalResponse.newBuilder()
                                .body(ResponseBody.create(body.contentType(), bodyString))
                                .build();

                    }
                }

                if (!NetWorkUtil.isConnected(AppInfoManager.getInstance().getContext())) {
                    if (originalResponse.isSuccessful()) {
                        CommonSimpleLog.w(TAG, "注意: " + request.url() + "响应源于缓存！！！", "flyme", CommonSimpleLog.getLABEL_NETWORK());
                    }
                }
                return originalResponse;

            } catch (Exception e) {
                e.printStackTrace();
                if (needEffectCache) {
                    HttpCacheBean cache = CommonEntityDaoHelper.get().getCommonData(key, "http_cache", HttpCacheBean.class);
                    if (cache != null && System.currentTimeMillis() - cache.createTime <= HttpKeyConstant.HTTP_CACHE_TIME_MILLIS) {
                        CommonSimpleLog.i(TAG, "使用有效缓存数据03: " + key, "flyme");
                        return new Response.Builder()
                                .request(request)
                                .code(200)
                                .message("")
                                .protocol(Protocol.HTTP_1_1)
                                .body(ResponseBody.create(MediaType.parse("application/json;charset=UTF-8"), cache.response))
                                .build();
                    }
                }
                throw e;
            }
        }
    };
    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheResponseControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isConnected(AppInfoManager.getInstance().getContext())) {
                //有网的时候读接口上的@Headers里的配置，您可以在这里进行统一的设置
                String cacheControl = request.header(HttpKeyConstant.RESPONSE_CACHE);
                if (!TextUtils.isEmpty(cacheControl)) {
                    cacheControl = cacheControl.replace(HttpKeyConstant.RESPONSE_CACHE, "Cache-Control");
                    return originalResponse.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                }
            }
            return originalResponse;
        }
    };

    public static class HttpCacheBean {
        private String key;

        private String response;

        private long createTime;

        public HttpCacheBean(String key, String response, long createTime) {
            this.key = key;
            this.response = response;
            this.createTime = createTime;
        }

        public String getKey() {
            return key;
        }

        public String getResponse() {
            return response;
        }

        public long getCreateTime() {
            return createTime;
        }
    }

}



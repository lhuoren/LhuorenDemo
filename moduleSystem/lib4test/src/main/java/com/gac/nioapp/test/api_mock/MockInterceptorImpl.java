package com.gac.nioapp.test.api_mock;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.syy.modulebase.http.constants.HttpUrlConstant;
import com.syy.modulebase.utils.TimeAsyncManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealResponseBody;

/**
 * @author heyufei
 * @version 2.0 增加 Yapi mock支持
 * @className MockInterceptor
 * @description API拦截模拟
 * @since 5/7/21 2:36 PM
 */
public class MockInterceptorImpl implements Interceptor, MockApiService {
    private final String TAG = "MockInterceptor";

    /**
     * 拦截规则
     */
    private final Map<String, String> mNodes = new HashMap<>();

    /**
     * Yapi 接口映射
     */
    private final Map<String, String> mYapiNodes = new HashMap<>();

    private String fixUrl = "";

    public MockInterceptorImpl() {
        initYapiConfig();
    }

    /**
     * yapi mock 接口映射关系
     */
    private void initYapiConfig() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Log.d(TAG, "initYapiConfig: ");
        Request request = new Request.Builder()
                .url("http://yapi.gacnio-inc.com/mock/216/hycan/android/apikv")
                .method("GET", null)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: 请求异常了", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String json = response.body().string();
                        Log.d(TAG, "onResponse: yapi 映射：" + json);
                        JSONArray array = new JSONArray(json);
                        Log.d(TAG, "onResponse: yapi 映射数量:" + array.length());

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject job = array.getJSONObject(i);
                            mYapiNodes.put(getUrl() + job.getString("api"), job.getString("mockapi"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG, "onResponse: 获取映射列表失败！", e);
                    }
                } else {
                    Log.e(TAG, "onResponse: 请求失败！" + response.code());
                }
            }
        });
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.url().toString();
        String fixUrl = url;
        if (mNodes.size() == 0) {
            Log.i(TAG, "url:" + url);
            return chain.proceed(request);
        }
        String getP = "";
        int subIdx = url.indexOf('?');
        if (subIdx >= 0) {
            fixUrl = url.substring(0, subIdx);
            getP = url.substring(subIdx);
        }


        String result = mNodes.get(fixUrl);
        if (result == null) {
            Log.d(TAG, "intercept: 不拦截:" + url);
            return chain.proceed(request);
        } else {
            Log.d(TAG, "intercept: 在拦截名单：" + url);
            String yapiRealUrl = mYapiNodes.get(fixUrl);

            //优先 yapi映射
            if (!TextUtils.isEmpty(yapiRealUrl)) {
                Log.e(TAG, "intercept: Yapi 截断：" + url);
                Request newR = request.newBuilder().url(yapiRealUrl + getP).build();
                return chain.proceed(newR);
            } else {
                if (!TextUtils.isEmpty(result)) {
                    Log.e(TAG, "intercept: 截断：" + url);
                    Log.e(TAG, "intercept: Mock数据：" + result);
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return new Response.Builder()
                            .code(200)
                            .request(request)
                            .message("mock 成功")
                            .receivedResponseAtMillis(TimeAsyncManager.getCurrentTimeMillis())
                            .sentRequestAtMillis(-1)
                            .protocol(Protocol.HTTP_1_1)
                            .body(RealResponseBody.create(MediaType.parse("application/json;charset=UTF-8"), result.getBytes(StandardCharsets.UTF_8)))
                            .build();
                } else {
                    Log.d(TAG, "intercept: 不拦截:" + url);
                    return chain.proceed(request);
                }
            }
        }

    }


    @Override
    public void regApi(String urlKey, String expectingBetterResults) {
        if (urlKey.startsWith("/")) {
            urlKey = urlKey.substring(1);
        }
        Log.d(TAG, "regApi: 注册：" + getUrl() + urlKey);
        if (TextUtils.isEmpty(expectingBetterResults)) {
            expectingBetterResults = "";
        }
        mNodes.put(getUrl() + urlKey, expectingBetterResults);
    }

    @Override
    public void unRegApi(String urlKey) {
        mNodes.remove(urlKey);
    }

    @Override
    public void unRegApi() {
        mNodes.clear();
    }

    @Override
    public void init(Context context) {

    }

    private String getUrl() {
        if (TextUtils.isEmpty(fixUrl)) {
            fixUrl = HttpUrlConstant.getAPPURL();
            String endUrl = "/";
            if (!fixUrl.endsWith(endUrl)) {
                fixUrl = fixUrl + endUrl;
            }
        }
        return fixUrl;
    }
}

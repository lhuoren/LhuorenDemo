package com.gac.nioapp.test.api_mock;

import android.content.Context;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.syy.modulebase.http.livedata.LiveDataRetrofitManager;
import com.gac.nioapp.test.DebugRouterConstant;
import com.syy.modulebase.http.HttpServiceGenerator;

import okhttp3.OkHttpClient;

/**
 * @author heyufei
 * @version 1.0
 * @className ApiMock
 * @description 屏蔽Mock Api细节
 * @since 5/8/21 11:21 AM
 */

@Route(path = DebugRouterConstant.PATH_MOCK_API)
public class ApiMockImpl implements MockApiService {
    MockInterceptorImpl mInterceptor;

    public ApiMockImpl() {
        mInterceptor = new MockInterceptorImpl();
        OkHttpClient client = HttpServiceGenerator.create()
                .getOkHttpClient()
                .newBuilder()
                .addInterceptor(mInterceptor)
                .build();

        HttpServiceGenerator.create().retrofit =
                HttpServiceGenerator.create().retrofit.newBuilder().client(client)
                        .build();
        HttpServiceGenerator.create().serviceCache.clear();
        LiveDataRetrofitManager.INSTANCE.setMockRetrofit(LiveDataRetrofitManager.INSTANCE.getMockRetrofit().newBuilder().client(client).build());
    }

    @Override
    public void regApi(String urlKey, @Nullable String expectingBetterResults) {
        mInterceptor.regApi(urlKey, expectingBetterResults);
    }

    @Override
    public void unRegApi(String urlKey) {
        mInterceptor.unRegApi(urlKey);
    }

    @Override
    public void unRegApi() {
        mInterceptor.unRegApi();
    }

    @Override
    public void init(Context context) {

    }
}

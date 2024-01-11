package com.gac.nioapp.test.api_mock;


import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @author heyufei
 * @version 1.0
 * @className MockApiService
 * @description TODO
 * @since 5/8/21 11:23 AM
 */
public interface MockApiService extends IProvider {
    void regApi(String urlKey, @Nullable String expectingBetterResults);

    void unRegApi(String urlKey);

    void unRegApi();

}

package com.gac.nioapp.test.kit

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.didichuxing.doraemonkit.kit.AbstractKit
import com.gac.nioapp.test.DebugRouterConstant
import com.gac.nioapp.test.R
import com.gac.nioapp.test.api_mock.MockApiService

/**
 * @className HycanAbstractKit
 * @description TODO
 * @author heyufei
 * @since 2021/12/3 3:51 下午
 * @version 1.0
 */
abstract class HycanAbstractKit : AbstractKit() {


    @Autowired(name = DebugRouterConstant.PATH_MOCK_API)
    protected lateinit var mockApi: MockApiService

    override val icon: Int
        get() = R.drawable.ic_baseline_sports_rugby_24


    override fun onAppInit(context: Context?) {
        ARouter.getInstance().inject(this)

    }
}
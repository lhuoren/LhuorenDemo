package com.gac.nioapp.test

import android.app.Activity
import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gac.nioapp.test.api_mock.MockApiService
import com.gac.nioapp.test.kit.impl.GacnioTestScheduler
import com.gac.nioapp.test.router.RouterConstant
import com.syy.modulebase.utils.ToastUtil

/**
 * @className FlyTestYearScheduler
 * @description Z03激活
 * @author lhuoren
 * @since 5/20/21 3:33 PM
 * @version 1.0
 */
@Route(path = RouterConstant.AROUTE_TEST_PATH)
class LhrMockSentryActiveScheduler : GacnioTestScheduler {

    @Autowired(name = DebugRouterConstant.PATH_MOCK_API)
    lateinit var mockApi: MockApiService
    private var needMock = false

    override fun onDragFloatBtnClick(context: Activity) {
    }

    override fun onDragFloatBtnLongClick(context: Activity) {
        needMock = true
        mockApi.regApi(
            "/vcp/sentry/simpleAuth/front/ssn/v3.2.13/openVideo",
            "{" +
                    "  \"msg\": \"success\"," +
                    "  \"code\": 0," +
                    "  \"data\": {" +
                    "    \"token\": \"04AAAAAGUfhk0AEBtg3yye3b5QITqsf1lQE18AcG3TEwM9pfalNV5+cTXIRW/toCGcYGNjnFCAl4bK4In5vRP0dTxZKSEcyzyHwKLgf57IJUBCCddQzf1p6hrVP7t5xBTKHmMI83k02gerkjZ9B+oyhwct/h3diOAxky2vV4wClmFz7AoZHXKtlTTWKto=\"," +
                    "    \"streamId\": null," +
                    "    \"vin\": \"LMWU11S81P1S00119\"," +
                    "    \"sessionId\": \"1710130240629583874\"," +
                    "    \"rtsAppId\": 1025059114" +
                    "  }" +
                    "}"
        )

        ToastUtil.toastShortMessage("mock 哨兵开启")
    }

    override fun init(context: Context?) {
        ARouter.getInstance().inject(this)
    }


    override fun onNextDragFloatBtnClick(context: Activity) {

        needMock = true

        mockApi.regApi(
            "/vcp/sentry/simpleAuth/front/ssn/v3.2.13/closeVideo",
            "{" +
                    "  \"msg\": \"success\"," +
                    "  \"code\": 0," +
                    "  \"data\": {" +
                    "    \"token\": null," +
                    "    \"streamId\": null," +
                    "    \"vin\": \"LMWU11S81P1S00119\"," +
                    "    \"sessionId\": \"1710130392383696898\"," +
                    "    \"rtsAppId\": null" +
                    "  }" +
                    "}"
        )

        ToastUtil.toastShortMessage("mock 哨兵关闭")

    }
}
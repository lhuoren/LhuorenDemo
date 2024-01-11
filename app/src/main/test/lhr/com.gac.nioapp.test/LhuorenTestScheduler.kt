package com.gac.nioapp.test

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.gac.nioapp.test.api_mock.MockApiService
import com.gac.nioapp.test.kit.impl.GacnioTestScheduler
import com.gac.nioapp.test.router.ARouterManager
import com.gac.nioapp.test.router.RouterConstant
import com.syy.modulebase.http.constants.HttpCodeConstant

/**
 * @ClassName DefaultTestScheduler
 * @Description TODO
 * @Author lhuoren
 * @Date 2021/5/18 10:27
 */

//@Route(path = RouterConstant.AROUTE_TEST_PATH)
class LhuorenTestScheduler : GacnioTestScheduler {

    @Autowired(name = DebugRouterConstant.PATH_MOCK_API)
    lateinit var mockApi: MockApiService

    private var needMock = false

    override fun init(context: Context?) {
        ARouter.getInstance().inject(this)
    }

    override fun onDragFloatBtnClick(context: Activity?) {
//        val url = "https://static.hycan.com.cn/linkurl/test-drive.html?id=144"
//        val ii = Intent()
//        ii.putExtra(RouterConstant.URL, url)
//        ii.putExtra(RouterConstant.TITLE, "")
//        ii.putExtra(RouterConstant.HIDE_TITLEBAR, true)
//        ARouterManager.getInstance()
//            .startARActivityPble(RouterConstant.PATH_TO_WEBVIEW_ACTIVITY, ii)

//        context?.let { BlePreCalibrationActivity.startActivity(it) }
    }

    override fun onDragFloatBtnLongClick(context: Activity?) {
        Toast.makeText(context, "哨兵模式", Toast.LENGTH_SHORT).show()
        goSentinelModel()

        needMock = true

//        mockApi.regApi(
//            "/commserv/qrCode/simpleAuth/front/v3.2.0/getQrTypeByContent",
//            "{\"msg\":\"success\",\"code\":0,\"data\":{}}"
//        )

//        mockApi.regApi(
//            "vcp/code/simpleAuth/front/ssn/v3.2.12/subQrcode",
//            "{\"msg\":\"xxf\",\"code\":0,\"data\":{\"name\":\"nicai\",\"vin\":\"vin_test00000000123567\",\"img\":\"https://flyisme.work/upload/2021/09/ac_pic_z03_car_nor-6a6bd452be354a24b60aea48d02e5485.png\"}}"
//        )
//
//        mockApi.regApi(
//            "vcp/code/simpleAuth/front/ssn/v3.2.12/subQrcodeConfirm",
//            "{\"msg\":\"success\",\"code\":0,\"data\":{}}"
//        )
//
//        ToastUtil.toastShortMessage("mock 激活流程接口")

    }

    override fun onNextDragFloatBtnClick(context: Activity?) {
        Toast.makeText(context, "恒温座舱", Toast.LENGTH_SHORT).show()
//        val starter = Intent(context, CarUnitedAssistantSettingActivity::class.java)
//        starter.putExtra(HttpKeyConstant.USER_AGENT, false)
//        context?.startActivity(starter)
//        goCarSetting()

//        goSpaParkingRecord()

//        goSentinelModel()
//        goHealthChargeActivity()

//        goQrScanTestActivity()

        goThermostaticCockpitActivity()

    }

    fun goQrScanTestActivity() {
        ARouterManager.getInstance()
            .startARActivityPble(DebugRouterConstant.QR_SCAN_TEST_ACTIVITY, Intent())
    }


    fun goThermostaticCockpitActivity() {
        ARouterManager.getInstance()
            .startARActivityPble(RouterConstant.PATH_TO_CAR_MAINTENANCE_THERMOSTATIC_COCKPIT, Intent())
    }

    /**
     * 去车辆设置页
     */
    fun goCarSetting() {
        ARouterManager.getInstance()
            .startARActivityPble(RouterConstant.PATH_TO_CAR_MAINTENANCE_MORE, Intent())
    }

    fun goHealthChargeActivity() {
        ARouterManager.getInstance()
            .startARActivityPble(RouterConstant.PATH_TO_HEALTH_CHARGE, Intent())
    }


    fun goSentinelModel() {
        ARouterManager.getInstance()
            .startARActivityPble(RouterConstant.PATH_TO_CAR_MAINTENANCE_SENTINEL_MODEL, Intent())
    }

    fun goSpaParkingRecord() {
        //监控记录
        val parkingRecordIntent = Intent()
        parkingRecordIntent.putExtra(
            "RECORD_TYPE",
            HttpCodeConstant.TYPE_PAGE_SPA_PARKING_RECORD_PAGE
        )
        ARouterManager.getInstance().startARActivityPble(
            RouterConstant.PATH_TO_CAR_MAINTENANCE_MONITORING_RECORD,
            parkingRecordIntent
        )
    }
}
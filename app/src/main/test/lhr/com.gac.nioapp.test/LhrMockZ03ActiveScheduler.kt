package com.gac.nioapp.test

import android.app.Activity
import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.gac.nioapp.test.api_mock.MockApiService
import com.gac.nioapp.test.kit.impl.GacnioTestScheduler
import com.syy.modulebase.utils.ToastUtil

/**
 * @className FlyTestYearScheduler
 * @description Z03激活
 * @author lhuoren
 * @since 5/20/21 3:33 PM
 * @version 1.0
 */
//@Route(path = RouterConstant.AROUTE_TEST_PATH)
class LhrMockZ03ActiveScheduler : GacnioTestScheduler {

    @Autowired(name = DebugRouterConstant.PATH_MOCK_API)
    lateinit var mockApi: MockApiService
    private var needMock = false

    override fun onDragFloatBtnClick(context: Activity) {
        //修改配置详情页
       /* CarMaintenanceActivateCarActivity.start(context, "xxxxx", 2)
        ToastUtil.toastShortMessage("激活车06")*/
//        CarActiveStatusRepository.instance.updateStatus("")
//        com.gac.nioapp.test.util.ToastUtil.toastShortMessage("撤销")
    }

    override fun onDragFloatBtnLongClick(context: Activity) {
//        CommonSimpleLog.i("LhrMockZ03ActiveScheduler","owner:"+(1 == VCPCarCommonRepository.instance.getCar()?.owner),"lhuoren")
//        CarMaintenanceActivateCarActivity.start(context, "xxxxx", 1)
//        com.gac.nioapp.test.util.ToastUtil.toastShortMessage("激活车03")
    }

    override fun init(context: Context?) {
        ARouter.getInstance().inject(this)
    }


    override fun onNextDragFloatBtnClick(context: Activity) {

        needMock = true

//        mockApi.regApi(
//            "/commserv/qrCode/simpleAuth/front/v3.2.0/getQrTypeByContent",
//            "{\"msg\":\"success\",\"code\":0,\"data\":{}}"
//        )

        mockApi.regApi(
            "vcp/code/simpleAuth/front/ssn/v3.2.12/subQrcode",
            "{\"msg\":\"xxf\",\"code\":0,\"data\":{\"name\":\"nicai\",\"vin\":\"vin_test00000000123567\",\"img\":\"https://flyisme.work/upload/2021/09/ac_pic_z03_car_nor-6a6bd452be354a24b60aea48d02e5485.png\"}}"
        )

        mockApi.regApi(
            "vcp/code/simpleAuth/front/ssn/v3.2.12/subQrcodeConfirm",
            "{\"msg\":\"success\",\"code\":0,\"data\":{}}"
        )

        ToastUtil.toastShortMessage("mock 激活流程接口")

        //18268184435:LNAMJAB3XM5ST0072; 18565366947,15521151685:LNAMJAB3XM5ST0072; LMWT11S26N1S00007
//        CarActiveStatusRepository.instance.updateStatus("G05TBOXEXCELFORE1")
//        ToastUtil.toastShortMessage("一步到位")
    }
}
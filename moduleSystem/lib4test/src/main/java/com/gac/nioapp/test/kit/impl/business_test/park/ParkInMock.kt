package com.gac.nioapp.test.kit.impl.business_test.park

import android.app.Activity
import com.gac.nioapp.test.R
import com.gac.nioapp.test.kit.HycanAbstractKit
import com.gac.nioapp.test.kit.IDeveloperKit
import com.gac.nioapp.test.repository.BluetoothCarParkingRepository
import com.gac.nioapp.test.repository.ParkInCallBack
import com.gac.nioapp.test.repository.ParkSelfCheckCallBack
import com.google.auto.service.AutoService
import com.syy.modulebase.http.messge.livedata.UnPeekLiveData
import com.syy.modulebase.utils.ToastUtil
import kotlinx.coroutines.*

/**
 * @className ParkOutMock
 * @description
 * 模拟泊入
 * @author heyufei
 * @since 2021/12/6 2:11 下午
 * @version 1.0
 */
@AutoService(IDeveloperKit::class)
class ParkInMock : IDeveloperKit {
    override fun getAbstractKit() = object : HycanAbstractKit() {
        override val name: Int
            get() = R.string.mock_park_in

        private var lastJob: Job? = null
        private var parkInIndex = 0

        override fun onClickWithReturn(activity: Activity): Boolean {

            lastJob?.cancel()
            lastJob = GlobalScope.launch(Dispatchers.Main) {
                BluetoothCarParkingRepository.instance.run {
                    delay(500)
                    //收到泊入请求
                    (parkInResult() as UnPeekLiveData).postValue(ParkInCallBack.ReceiveParkIn)
                    if (parkInIndex++ % 2 == 0) {
                        ToastUtil.toastShortMessage("自检失败")
                        delay(1000 * 4L)

                        (parkSelfCheckResult() as UnPeekLiveData).postValue(
                            ParkSelfCheckCallBack.ParkSelfCheckError(
                                ArrayList()
                            )
                        )
                    }
                    delay(1000L * 2)
                    //自检通过
                    ToastUtil.toastShortMessage("自检通过")
                    (parkSelfCheckResult() as UnPeekLiveData).postValue(ParkSelfCheckCallBack.ParkSelfCheckSuccessful)
                    delay(1000 * 15L)
                    //泊入完成
                    ToastUtil.toastShortMessage("泊入完成")
                    (parkInResult() as UnPeekLiveData).postValue(ParkInCallBack.ParkInCompleteCarStop)
                }
            }

            return true
        }
    }
}
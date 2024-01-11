package com.gac.nioapp.test.repository

import com.gac.common.utils.CommonSimpleLog
import com.gac.nioapp.test.bean.CarOrderDataBean
import com.gac.nioapp.test.bean.SelectOrderCarsBean
import com.gac.nioapp.test.extension.DataErrorNeedRetryException
import com.gac.nioapp.test.repository.api.ICarOrderService
import com.syy.modulebase.http.coroutines.CoroutinesRetrofitManager
import com.syy.modulebase.utils.AppInfoManager
import com.syy.modulebase.utils.NetWorkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @className CarOrderRepository
 * @description TODO
 * @author heyufei
 * @since 2021/12/31 5:28 下午
 * @version 1.0
 */
class CarOrderRepository {
    private val TAG = "CarOrderRepository"

    private val service by lazy {
        CoroutinesRetrofitManager.create(ICarOrderService::class.java)
    }

    suspend fun refreshOrders(): CarOrderDataBean<SelectOrderCarsBean>? {


        return withContext(Dispatchers.IO) {


            val orders = try {
                if (!NetWorkUtil.isConnected(AppInfoManager.getInstance().getContext())) {
                    null
                } else {
                    service.carOrders("2")
                }
            } catch (e: Exception) {
                CommonSimpleLog.e(TAG, "refreshOrders Error: $e", "flyme")
                e.printStackTrace()
                throw DataErrorNeedRetryException("订单获取失败")
            }
            if (orders?.code == 0 && orders.data != null) {
                orders.data
            } else {
                null
            }
        }
    }
}
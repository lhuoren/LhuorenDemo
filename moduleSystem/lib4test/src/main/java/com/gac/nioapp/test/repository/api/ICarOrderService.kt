package com.gac.nioapp.test.repository.api

import com.gac.nioapp.test.bean.CarOrderDataBean
import com.gac.nioapp.test.bean.SelectOrderCarsBean
import com.syy.modulebase.http.bean.HttpResult
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @className ICarOrderService
 * @description TODO
 * @author heyufei
 * @since 2021/12/31 5:08 下午
 * @version 1.0
 */
interface ICarOrderService {
    @GET("/order/carOrderSwitch/simpleAuth/front/v3.2.0/carOrders")
    suspend fun carOrders(@Query("queryNewCarType") queryNewCarType: String): HttpResult<CarOrderDataBean<SelectOrderCarsBean>>
}
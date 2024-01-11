package com.gac.nioapp.test.car_maintenance

import com.gac.nioapp.test.bean.SelectOrderCarsBean
import kotlinx.coroutines.flow.Flow

/**
 * @className ICarOrder
 * @description
 * 控制具体显示哪个订单数据
 * @author heyufei
 * @since 2021/12/30 4:10 下午
 * @version 1.0
 */
interface ICarHomeOrder : ICarHomeBasePage {

    /**
     * 更新车辆订单
     */
    suspend fun updateCarOrders(): Flow<List<SelectOrderCarsBean>>

    //是否有车辆订单
    fun hasCarOrder(): Boolean

    /**
     * 根据订单id 展示具体的订单页面
     * orderId为null时随机展示一个订单
     * orderId有效时 会更新持久化存储的orderId
     */
    fun showCarOrder(orderId: String? = null): Boolean

    /**
     * 判断该orderId是否存在对应的订单（心愿单或普通订单）
     */
    fun orderExist(orderId: String? = null): Boolean


    /**
     * 获取上一次展示订单页面时的订单号
     */
    fun getLastShowCarOrderId(): String


    /**
     * 标志退出登陆了，此时应该重置订单数据
     */
    fun logOut()

}
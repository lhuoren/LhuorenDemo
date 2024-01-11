package com.gac.nioapp.test.car_maintenance

import android.app.Application
import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.gac.nioapp.test.bean.CarModelItemBean

/**
 * @className ICarModel
 * @description 车型
 * 每款新增车辆都需要实现该类
 * @author heyufei
 * @since 2021/12/31 11:09 上午
 * @version 1.0
 */
interface ICarModel {

    //车型
    val carType: String

    //数据刷新（重新登陆时），注意：更新失败会抛异常
    suspend fun refreshData(): Boolean

    //获取可控车辆数，同步方法，数据在 refreshData时已经更新完毕
    fun getControllableCarList(): List<CarModelItemBean>

    //登出
    fun logOut()

    /**
     * vinCode: 待显示车辆vinCode,可能为null
     * return : 该车型对应的fragment
     */
    fun getShowFragment(vinCode: String?): Fragment

    //是否包含该车辆
    fun containsCar(vinCode: String) = getControllableCarList().find { it.id == vinCode } != null


    /**
     * 是否可以显示指定vin码的车辆
     * vinCode：null时，会尝试显示车辆列表第一台车
     * return：返回可以成功显示的vin码，为null时，不可显示
     */
    fun canYouShow(vinCode: String?) = run {
        when {
            TextUtils.isEmpty(vinCode) -> {
                getControllableCarList().firstOrNull()?.id
            }

            containsCar(vinCode!!) -> {
                vinCode
            }

            else -> {
                null
            }
        }
    }

    fun init(context: Application)

    fun getCarPosition(vinCode: String, callBack: CarPositionCallBack)

    interface CarPositionCallBack {
        fun location(
            latitude: Double,
            longitude: Double,
            carType: String
        )
    }
}
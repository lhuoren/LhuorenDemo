package com.gac.nioapp.test.car_maintenance

import com.gac.nioapp.test.bean.CarModelItemBean
import kotlinx.coroutines.flow.Flow


/**
 * @className ICarHome
 * @description
 * 控制具体显示哪台车
 * @author heyufei
 * @since 2021/12/30 3:32 下午
 * @version 1.0
 */
interface ICarHomeControl : ICarHomeBasePage {

    suspend fun updateCars(): Flow<List<CarModelItemBean>>

    //是否有可控车辆
    fun hasControllableCar(): Boolean

    /**
     * 可控车辆数(所有车型)
     */
    fun getAllControllableCarList(): List<CarModelItemBean>

    /**
     * 根据vin码展示具体车辆
     * vinCode:为null时。随机展示一个可控车
     * false：展示失败
     * return 是否完成展示
     */
    fun showCar(vinCode: String? = null): Boolean

    fun carExist(vinCode: String? = null): Boolean


    /**
     * 获取上一次展示车辆的vinCode
     */
    fun getLastCarId(): String

    /**
     * 标志退出登陆了，此时应该重置车辆数据
     */
    fun logOut()

    /**
     * 注册车控模块
     */
    fun registerCarModel(model: ICarModel)

    /**
     * 获取车辆
     */
    fun getCarModelByType(type: String): ICarModel?

    /**
     * 根据vin码获取车型
     */
    fun getCarModelByVinId(vinId: String): ICarModel?


    /**
     * 获取车辆位置信息，失败时返回[0.0,0.0]
     */
    fun getCarPosition(vinCode: String, callback: ICarModel.CarPositionCallBack)

    /**
     * 获取车系
     */
    fun getVehicleSeries(vinCode: String): String
}
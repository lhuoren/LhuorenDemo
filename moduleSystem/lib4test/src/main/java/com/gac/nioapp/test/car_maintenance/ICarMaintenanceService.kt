package com.gac.nioapp.test.car_maintenance

import android.content.Context
import androidx.fragment.app.Fragment
import com.gac.nioapp.test.bean.G06SocLimitBean
import com.gac.nioapp.test.bean.HycanCarItemBean
import com.gac.nioapp.test.bean.RequestSocLimit

/**
 * @className ICarMaintenanceService
 * @description 定义新版车机模块(business:car_maintenance)对外提供的功能
 * @author heyufei
 * @since 2021/8/11 8:13 下午
 * @version 1.0
 */
interface ICarMaintenanceService {
    //暴露爱车页
    fun provideCarCenterFragment(): Fragment

    //更新车辆
    fun updateSelectCar(car: HycanCarItemBean)

    //获取当前车辆vin码
    fun getVin(): String

    /**
     * 扫码登陆
     */
    fun qrLogin(qrText: String, carType: Int)

    /**
     * 泊车请求 03
     */
    fun startSpa03Parking(isParkIn: Boolean)


    /**
     * 泊车请求 06
     * '1'   :遥控泊车
     * '1.5' :直进直出
     * '2.5' :SPA2.5
     */
    fun startSpa06Parking(parkType: String, needStartPark: Boolean = true)

    /**
     * 扫码激活
     */
    fun qrActive(qrText: String, carType: Int)

    /**
     * 打开授权用车人页面
     */
    fun startAuthorizedToAcceptActivity(
        context: Context,
        avator: String,
        nickname: String,
        authTime: String,
        innerPic: String,
    )

    /**
     * spa泊车页
     */
    fun startSpaRemoteControlParking()

    /**
     * 已经同意了Spa泊车
     */
    fun saveHasAgreeSpaDescription()

    /**
     * 去新电途H5
     * isReback:是否是重新打开，仅在qrCode不为null时生效
     */
    fun goNewElectricityWay(qrCode: String? = null, isReback: Boolean = false)

    /**
     * 查询健康充电
     */
    fun querySoc(vin: String, success: (data: G06SocLimitBean) -> Unit, fail: (msg: String) -> Unit)

    /**
     * 更新健康充电
     */
    fun updateSoc(model: RequestSocLimit, success: () -> Unit, fail: (msg: String) -> Unit)

    /**
     * 去我的充电桩
     */
    fun goMyChargingPile()

    /**
     * 去钥匙分享
     */
    fun goKeyToShare()

    /**
     * 去哨兵模式
     */
    fun goSentryModel()

    /**
     * 去道路救援
     */
    fun goRoadside()

    /**
     * 去预约维保
     */
    fun goMaintenance(vin: String? = null)


    fun connectEc(url: String)

    //驾驶周报
    fun goDrivingWeekly()

    fun goChargeView(id:Int?)

}
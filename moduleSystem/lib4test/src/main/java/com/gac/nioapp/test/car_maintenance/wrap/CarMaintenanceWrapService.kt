package com.gac.nioapp.test.car_maintenance.wrap

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.gac.nioapp.test.bean.G06SocLimitBean
import com.gac.nioapp.test.bean.HycanCarItemBean
import com.gac.nioapp.test.bean.RequestSocLimit
import com.gac.nioapp.test.car_maintenance.ICarMaintenanceService
import com.syy.modulebase.manager.ConstantsPath

/**
 * @className CarMaintenanceWrapService
 * @description 提供银基车控模块服务
 * @author heyufei
 * @since 2021/8/11 8:17 下午
 * @version 1.0
 */
class CarMaintenanceWrapService private constructor() : ICarMaintenanceService {

    @Autowired(name = ConstantsPath.CAR_MAINTENANCE_PATH)
    protected lateinit var serviceIProvider: ICarMaintenanceProviderService
    override fun qrActive(qrText: String, carType: Int) {
        serviceIProvider.qrActive(qrText, carType)
    }

    override fun startAuthorizedToAcceptActivity(
        context: Context,
        avator: String,
        nickname: String,
        authTime: String,
        innerPic: String
    ) {
        serviceIProvider.startAuthorizedToAcceptActivity(
            context,
            avator,
            nickname,
            authTime,
            innerPic
        )
    }

    override fun startSpaRemoteControlParking() {
        serviceIProvider.startSpaRemoteControlParking()
    }

    override fun saveHasAgreeSpaDescription() {
        serviceIProvider.saveHasAgreeSpaDescription()
    }

    override fun goNewElectricityWay(qrCode: String?, isReback: Boolean) {
        serviceIProvider.goNewElectricityWay(qrCode, isReback)

    }

    override fun querySoc(
        vin: String,
        success: (data: G06SocLimitBean) -> Unit,
        fail: (msg: String) -> Unit
    ) {
        serviceIProvider.querySoc(vin, success, fail)
    }

    override fun updateSoc(
        model: RequestSocLimit,
        success: () -> Unit,
        fail: (msg: String) -> Unit
    ) {
        serviceIProvider.updateSoc(model, success, fail)
    }

    override fun goMyChargingPile() {
        serviceIProvider.goMyChargingPile()
    }

    override fun goKeyToShare() {
        serviceIProvider.goKeyToShare()
    }

    override fun goSentryModel() {
        serviceIProvider.goSentryModel()
    }

    override fun goDrivingWeekly() {
        serviceIProvider.goDrivingWeekly()
    }

    override fun goRoadside() {
        serviceIProvider.goRoadside()
    }


    override fun goMaintenance(vin: String?) {
        serviceIProvider.goMaintenance(vin)
    }

    override fun connectEc(url: String) {
        serviceIProvider.connectEc(url)
    }

    override fun goChargeView(id:Int?) {
        serviceIProvider.goChargeView(id)
    }

    //提供爱车页Fragment
    override fun provideCarCenterFragment() = serviceIProvider.provideCarCenterFragment()
    override fun updateSelectCar(car: HycanCarItemBean) {
        serviceIProvider.updateSelectCar(car)
    }

    override fun getVin() = serviceIProvider.getVin()
    override fun qrLogin(qrText: String, carType: Int) {
        serviceIProvider.qrLogin(qrText, carType)
    }

    override fun startSpa03Parking(isParkIn: Boolean) {
        serviceIProvider.startSpa03Parking(isParkIn)
    }

    override fun startSpa06Parking(type: String, needStartPark: Boolean) {
        serviceIProvider.startSpa06Parking(type, needStartPark)
    }

    init {
        ARouter.getInstance().inject(this)
    }


    companion object {
        @JvmStatic
        val instance by lazy {
            CarMaintenanceWrapService()
        }
    }

}
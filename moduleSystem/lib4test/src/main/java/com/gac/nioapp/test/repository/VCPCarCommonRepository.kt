package com.gac.nioapp.test.repository

import android.os.Looper
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gac.common.utils.CommonSimpleLog
import com.gac.nioapp.test.bean.HycanCarItemBean
import com.gac.nioapp.test.utils.ColorPicHelper
import com.syy.modulebase.manager.UserInfoManager

/**
 * @className VCPCarCommonRepository
 * @description 车控基本数据提供
 * @author heyufei
 * @since 2021/8/11 2:29 下午
 * @version 1.0
 */
class VCPCarCommonRepository private constructor() {
    private val TAG = "VCPCarCommonRepository"

    companion object {
        @JvmStatic
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { VCPCarCommonRepository() }
    }

    private val vinCode = MutableLiveData<String>()
    private var defaultCar: HycanCarItemBean? = null

    init {
        UserInfoManager.getInstance().unPeekLoginLiveStatus.observeForever {
            if (it == false) {
                defaultCar = null
                vinCode.value = "vin_log_out"
            }
        }
    }

    /**
     * 更新默认选中的车
     */
    fun updateSelectCar(car: HycanCarItemBean) {
        this.defaultCar = car
        if (car.vin != vinCode.value) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                this.vinCode.value = car.vin
            } else {
                this.vinCode.postValue(car.vin)
            }
        }
        check(car)
    }

    private fun check(car: HycanCarItemBean) {
        if (TextUtils.isEmpty(car.vehicleSeries)) {
            CommonSimpleLog.e(
                TAG,
                "check: ${car.vin},vehicleSeries is null",
                "flyme",
                CommonSimpleLog.LABEL_BUSINESS,
            )
        }
        if (TextUtils.isEmpty(car.vehicleColor)) {
            CommonSimpleLog.e(
                TAG,
                "check: ${car.vin},vehicleColor is null",
                "flyme",
                CommonSimpleLog.LABEL_BUSINESS,
            )
        }
    }


    /**
     * 拿到vin码
     */
    fun getVin() = defaultCar?.vin ?: "no_id"

    /**
     * 拿到车型
     */
    fun getVehicleSeries() = defaultCar?.vehicleSeries ?: "no_vehicleSeries"

    /**
     * 拿到可观测的vin码
     */
    fun getVinLive(): LiveData<String> = vinCode

    /**
     * 拿到车
     */
    fun getCar() = defaultCar


    fun reFreshCarName(nickName: String) {
        if (defaultCar == null) {
            return
        }
        var defaultConfigs = defaultCar?.authorConfigs
        if (defaultConfigs.isNullOrEmpty()) {
            defaultConfigs = ArrayList()
        }
        updateSelectCar(defaultCar!!.copy(carNickname = nickName, authorConfigs = defaultConfigs))
    }
}


/**
 * 注意 "当前可控车辆"
 * 注意 "当前可控车辆"
 * 注意 "当前可控车辆"
 *
 * 激活场景,及多车辆切换时不可用
 * 扩展方法获取当前可控车辆默认车辆 车图
 */
fun ColorPicHelper.getResByDefaultCar(key: String) =
    this.getResByCar(VCPCarCommonRepository.instance.getCar(), key)

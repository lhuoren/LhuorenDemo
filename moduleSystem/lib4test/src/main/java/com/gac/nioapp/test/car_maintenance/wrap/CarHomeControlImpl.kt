package com.gac.nioapp.test.car_maintenance.wrap

import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import cn.gacnio.it.database.SPUtils
import com.gac.common.utils.CommonSimpleLog
import com.gac.nioapp.test.bean.CarModelItemBean
import com.gac.nioapp.test.bean.HycanCarItemBean
import com.gac.nioapp.test.car_maintenance.Coroutines2JavaCallback
import com.gac.nioapp.test.car_maintenance.ICarHomeControl
import com.gac.nioapp.test.car_maintenance.ICarModel
import com.gac.nioapp.test.car_maintenance.coroutines2Java
import com.gac.nioapp.test.extension.DataErrorNeedRetryException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import java.lang.ref.WeakReference

/**
 * @className CarHomeImpl
 * @description
 * 显示具体车型实现类
 * @author heyufei
 * @since 2021/12/31 11:04 上午
 * @version 1.0
 */
class CarHomeControlImpl private constructor() : ICarHomeControl {

    private val TAG = "CarHomeControlImpl"


    companion object {
        @JvmStatic
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { CarHomeControlImpl() }
    }

    private val carModels = HashSet<ICarModel>()
    private val LAST_SHOWCAR_VINCODE = "LAST_SHOWCAR_VINCODE"
    private var carUpdateFinish = false

    private var curCarModel: ICarModel? = null
    private var hostFragment: WeakReference<Fragment>? = null
    private var root_id = -1
    private val controlCarList = ArrayList<CarModelItemBean>()


    override fun registerCarModel(model: ICarModel) {
        CommonSimpleLog.i(TAG, "registerCarModel: ${model.carType}", "flyme")
        carModels.add(model)
    }

    override fun getCarModelByType(type: String) = carModels.find { it.carType == type }

    override fun getCarPosition(vinCode: String, callback: ICarModel.CarPositionCallBack) {
        val carModel = getCarModelByVinId(vinCode)
        if (carModel == null) {
            callback.location(0.0, 0.0, "")
        } else {
            carModel.getCarPosition(vinCode, callback)
        }
    }

    override fun getVehicleSeries(vinCode: String): String {
        val temp = findCarByVin(vinCode) ?: return "UnKnowType"
        if ("007" == temp.carType) {
            return "007"
        }
        return (temp.data as HycanCarItemBean).vehicleSeries
    }

    override fun getCarModelByVinId(vinCode: String) = carModels.find { it.containsCar(vinCode) }


    fun getCars(callback: Coroutines2JavaCallback<List<CarModelItemBean>>) {
        coroutines2Java(::updateCars, callback)
    }

    override suspend fun updateCars(): Flow<List<CarModelItemBean>> {
        return flow<List<CarModelItemBean>> {

            controlCarList.clear()
            try {
                carModels.forEach {
                    if (it.refreshData()) {
                        controlCarList.addAll(it.getControllableCarList())
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "updateCars: Error: xxx", e)
                carUpdateFinish = false
                controlCarList.clear()
                throw e
            }

            Log.d(TAG, "updateCars: ${carModels.size}")
            carUpdateFinish = true
            emit(controlCarList)
        }.flowOn(Dispatchers.IO)
            .retry(2) { e ->
                //异常时重试两次
                e is DataErrorNeedRetryException || e is NullPointerException
            }
    }

    override fun carExist(vinCode: String?): Boolean {
        if (TextUtils.isEmpty(vinCode)) {
            return false
        }
        return carModels.find {
            it.getControllableCarList().isNotEmpty()
        } != null
    }


    override fun hasControllableCar(): Boolean {
        return carUpdateFinish && getAllControllableCarList().isNotEmpty()
    }

    /**
     * 获取所有车控数量
     */
    override fun getAllControllableCarList() = controlCarList

    override fun showCar(vinCode: String?): Boolean {
        Log.d(TAG, "showCar: 查找：$vinCode")
        curCarModel = null
        val fragment = hostFragment?.get() ?: return false
        fragment.context ?: return false

        curCarModel = if (TextUtils.isEmpty(vinCode)) {
            carModels.find {
                it.getControllableCarList().isNotEmpty()
            }
        } else {
            carModels.find {
                it.getControllableCarList().isNotEmpty() && it.containsCar(vinCode!!)
            }
        }

        if (curCarModel != null) {
            val vC = curCarModel!!.canYouShow(vinCode)
            if (!TextUtils.isEmpty(vC)) {
                SPUtils.put(LAST_SHOWCAR_VINCODE, vC)
            }
            if (fragment.childFragmentManager.findFragmentByTag(curCarModel!!.carType + vinCode) == null) {
                fragment.childFragmentManager.beginTransaction()
                    .replace(
                        root_id,
                        curCarModel!!.getShowFragment(vC),
                        curCarModel!!.carType + vinCode
                    )
                    .commit()
            }
            CommonSimpleLog.i(TAG, "showCar: ${curCarModel!!.carType},$vC", "flyme")
            return true
        }
        return false
    }

    override fun getLastCarId(): String {
        return SPUtils.getString(LAST_SHOWCAR_VINCODE, "")
    }

    override fun logOut() {
        carModels.forEach {
            it.logOut()
        }
        SPUtils.put(LAST_SHOWCAR_VINCODE, "")
        carUpdateFinish = false
        curCarModel = null
        controlCarList.clear()
    }

    override fun bindHostFragment(fragment: Fragment, id: Int) {
        this.hostFragment = WeakReference(fragment)
        this.root_id = id
    }


    fun findCarByVin(vinCode: String) = controlCarList.find { it.id == vinCode }
}
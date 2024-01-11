package com.gac.nioapp.test.utils

import android.text.TextUtils
import cn.gacnio.it.database.SPUtils
import com.gac.common.utils.CommonSimpleLog
import com.gac.nioapp.test.bean.CarColour
import com.gac.nioapp.test.bean.HycanCarItemBean
import com.gac.nioapp.test.car_maintenance.wrap.CarHomeControlImpl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * 仅适配非007车型
 * eg:G05,G06
 */
object ColorPicHelper {

    private const val TAG = "ColorPicHelper"

    //G05 颜色映射到 G05-A
    private val car05_05A = HashMap<String, String>().apply {
        //W08,W09,B59,B13,DG2,BR3,WB9,KG3,DB1,
        put("W08", "W08")
        put("B29", "W08")
        put("W09", "W09")
        put("B30", "W09")
        put("B42", "W09")
        put("B59", "B59")
        put("B13", "B13")
        put("B6L", "B13")
        put("DG2", "DG2")
        put("BR3", "BR3")
        put("W23", "BR3")
        put("WB9", "WB9")
        put("W02", "WB9")
        put("W03", "WB9")
        put("KG3", "KG3")
        put("KG2", "KG3")
    }

    private val colorData = HashMap<String, Map<String, String>>().apply {
        val cacheData = SPUtils.getString(
            CarConstantUtil.COLOR_PIC_PATH + "_2", "[]"
        )
        if (cacheData != null) {
            try {
                Gson().fromJson<List<CarColour>>(
                    cacheData,
                    object : TypeToken<ArrayList<CarColour>>() {}.type
                ).forEach {
                    val colorDetails = HashMap<String, String>()
                    it.carColourDetails.forEach { carColourItem ->
                        carColourItem.picDetail.forEach { picItem ->
                            colorDetails[carColourItem.colourCode + "_" + picItem.picLocation] =
                                picItem.picPath
                        }
                    }
                    this[it.carSeries] = colorDetails
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 图片更新,预处理
     */
    fun updatePics(data: List<CarColour>?) {
        synchronized(colorData) {
            if (!data.isNullOrEmpty()) {
                //save cache
                SPUtils.put(CarConstantUtil.COLOR_PIC_PATH + "_2", Gson().toJson(data))
                colorData.clear()
                data.forEach {
                    val colorDetails = HashMap<String, String>()
                    it.carColourDetails.forEach { carColourItem ->
                        carColourItem.picDetail.forEach { picItem ->
                            colorDetails[carColourItem.colourCode + "_" + picItem.picLocation] =
                                picItem.picPath
                        }
                    }
                    colorData[it.carSeries] = colorDetails
                }
            }
            CommonSimpleLog.d(TAG, "updatePics: ${colorData.keys.toString()}", "flyme")
        }
    }

    /**
     * 获取具体图片或json动画
     * vehicleSeries:车系:"G05","G06"
     * vehicleColor:色系
     * key:键值
     */
    fun getRes(
        vehicleSeries: String,
        vehicleColor: String?, key: String
    ): String? {
        var fixVehicleColor = vehicleColor
        var fixVehicleSeries = vehicleSeries

        if (fixVehicleSeries.startsWith("G06")) {
            fixVehicleSeries = "G06"
        }

        //默认值
        if (TextUtils.isEmpty(fixVehicleColor)) {
            when (vehicleSeries) {
                "G05" -> {
                    fixVehicleColor = "KG2"
                }

                "G06" -> {
                    fixVehicleColor = "B22"
                }

                "G08" -> {
                    fixVehicleColor = "DW1"
                }

                "G05-A" -> {
                    fixVehicleColor = "KG3"
                }
            }
        }
        if (fixVehicleSeries == "G05" && key.startsWith("pic06")) {
            fixVehicleColor = car05_05A[fixVehicleColor ?: ""] ?: "KG3"
            fixVehicleSeries = "G05-A"
        }

        if (colorData.size == 0) {
            CommonSimpleLog.e(TAG, "getColorData Exception: list size = 0.", "flyme")
        }
        val result = colorData[fixVehicleSeries]?.get(fixVehicleColor + "_" + key)
        return if (TextUtils.isEmpty(result)) {
            CommonSimpleLog.e(
                TAG,
                "getRes: color miss :$fixVehicleSeries,${fixVehicleColor + "_" + key}",
                "flyme"
            )
            ColorPicHelperDefaultData.getDefaultPics(fixVehicleSeries, key, fixVehicleColor)
        } else {
            result
        }
    }

    /**
     * 通过vin码 key
     * 获取具体图片或loti资源
     */
    fun getResByVin(
        vin: String, key: String
    ): String? {
        val carData = CarHomeControlImpl.instance.findCarByVin(vin)?.data
        if (carData != null) {
            try {
                val carItem = carData as HycanCarItemBean
                return getResByCar(carItem, key)
            } catch (e: Exception) {
                CommonSimpleLog.e("getResByVin", "getResByVin: $vin,$key,${e.message}", "flyme")
            }
        }
        return null
    }

    /**
     * 获取具体图片或json动画
     * key:键值
     */
    fun getResByCar(
        car: HycanCarItemBean?, key: String
    ) = getRes(car?.vehicleSeries ?: "", car?.vehicleColor, key)
}
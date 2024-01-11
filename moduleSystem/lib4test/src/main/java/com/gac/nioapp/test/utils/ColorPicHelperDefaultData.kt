package com.gac.nioapp.test.utils

import android.text.TextUtils
import com.gac.common.utils.CommonSimpleLog
import com.gac.nioapp.test.bean.CarColour
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.syy.modulebase.BaseApplication
import com.syy.modulebase.http.bean.HttpResult
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * 仅适配非007车型
 * eg:G05,G06
 */
internal object ColorPicHelperDefaultData {
    private val TAG = "ColorPicHelperDefaultData"
    private val colorData = HashMap<String, Map<String, String>>()

    private val defalutColourCode = HashMap<String, String>().apply {
        put("G05", "KG2")
        put("G06", "B22")
        put("G08", "DW1")
        put("G05-A", "KG3")
    }

    init {
        val context = BaseApplication.getInstance()
        val inputStream: InputStream = context.assets.open("car_pic_config.json")

        val reader = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            sb.append(line)
        }
        reader.close()
        inputStream.close()
        val json = sb.toString()
        val gson = Gson()
        gson.fromJson<HttpResult<List<CarColour>>>(
            json,
            object : TypeToken<HttpResult<List<CarColour>>>() {}.type
        ).data?.forEach {
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


    //数据异常时兼容
    fun getDefaultPics(vehicleSeries: String, key: String, color: String? = ""): String? {
        val res = colorData[vehicleSeries]?.get(color + "_$key")
        if (TextUtils.isEmpty(res)) {
            CommonSimpleLog.e(TAG, "getDefaultPics: 异常颜色:$vehicleSeries,$key,$color", "flyme")
            return colorData[vehicleSeries]?.get(defalutColourCode[vehicleSeries] + "_$key")
        }
        return res
    }


}
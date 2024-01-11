package com.gac.nioapp.test.bean

import java.io.Serializable

/**
 * @className YgCarItem
 * @description 银基车辆接口返回
 * @author heyufei
 * @since 2021/8/4 4:51 下午
 * @version 1.0
 */
data class HycanCarItemBean(
    var authorConfigs: List<Int>,
    val authorUseId: Int,
    val carNickname: String,
    val plateNumber: String?,
    val owner: Int,
    val status: Int,
    val vehicleSeries: String,//车系名称 "G05"(Z03) "G06"
    val vehicleColor: String? = null,
    val versionName: String,
    val vin: String,
    val tinyId: String?,
    val carPic: String,
    val carSeatPic: String,
) : Serializable

fun HycanCarItemBean?.isCarOwner() = 1 == this?.owner
fun HycanCarItemBean.subNickName(maxLength: Int) =
    if (carNickname.length < maxLength) carNickname else "${
        carNickname.subSequence(
            0,
            maxLength
        )
    }..."

fun HycanCarItemBean?.isG06() = "G06" == this?.vehicleSeries
fun HycanCarItemBean?.isG05() = "G05" == this?.vehicleSeries
fun HycanCarItemBean?.isG05_A() = "G05-A" == this?.vehicleSeries
fun HycanCarItemBean?.isG08() = "G08" == this?.vehicleSeries


fun HycanCarItemBean?.defaultName() = when (this?.vehicleSeries) {
    "G05" -> "Z03 "
    "G05-A" -> "Z03 "
    "G06" -> "A06 "
    "G06-A" -> "A06 "
    "G08" -> "V09 "
    else -> ""
}

fun HycanCarItemBean?.haveBlueToothControlAbility() =
    true == this?.authorConfigs?.contains(2) || true == this?.authorConfigs?.contains(5)

fun HycanCarItemBean?.haveRemoteControlAbility() =
    true == this?.authorConfigs?.contains(1) || true == this?.authorConfigs?.contains(4)

fun HycanCarItemBean?.haveCarPositionAbility() =
    true == this?.authorConfigs?.contains(3) || true == this?.authorConfigs?.contains(14)
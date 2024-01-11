package com.gac.nioapp.test.repository


/**
 * @className ParkInCallBack
 * @description 泊车：泊入回调
 * @author heyufei
 * @since 2021/8/3 1:38 下午
 * @version 1.0
 */
sealed class ParkInCallBack {
    /**
     * 手机收到车机泊入消息
     */
    object ReceiveParkIn : ParkInCallBack()

    /**
     * 手机收到撤销泊入消息
     */
    object RevokeParkIn : ParkInCallBack()

    /**
     * 泊入到车位(车辆停止移动，未下电)
     */
    object ParkInCompleteCarStop : ParkInCallBack()

    /**
     * 下电完成
     * 泊入完成(warningOptions: MutableList<IngeekParkInWarning>)
     */
//    data class ParkInCompleteWarning(val warningOptions: MutableList<IngeekParkInWarning>) :
//        ParkInCallBack()


}
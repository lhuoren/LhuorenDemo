package com.gac.nioapp.test.repository

/**
 * @className ParkingCallBack
 * @description 泊车过程中的回调
 * @author heyufei
 * @since 2021/8/3 3:36 下午
 * @version 1.0
 */
sealed class ParkingCallBack {

    /**
     * 暂停
     */
    data class ParkPause(val errorOptions: MutableList<Exception>) : ParkingCallBack()

    /**
     * 暂停恢复
     */
    object ParkResume : ParkingCallBack()

    /**
     * 泊车失败
     */
    data class ParkStop(val errorOptions: MutableList<Exception>) : ParkingCallBack()
}
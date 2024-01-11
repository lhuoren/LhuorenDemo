package com.gac.nioapp.test.repository

/**
 * @className ParkSelfCheckCallBack
 * @description 泊车自检
 * @author heyufei
 * @since 2021/8/3 3:29 下午
 * @version 1.0
 */
sealed class ParkSelfCheckCallBack {
    /**
     * 自检通过无异常
     */
    object ParkSelfCheckSuccessful : ParkSelfCheckCallBack()

    /**
     * 自检未通过
     */
//    data class ParkSelfCheckError(val errorOptions: MutableList<IngeekException>) :
//        ParkSelfCheckCallBack()

    data class ParkSelfCheckError(val errorOptions: MutableList<Exception>) :
        ParkSelfCheckCallBack()
}
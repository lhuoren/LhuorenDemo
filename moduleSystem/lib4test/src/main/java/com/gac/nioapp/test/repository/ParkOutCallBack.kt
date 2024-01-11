package com.gac.nioapp.test.repository


/**
 * @className ParkOutCallBack
 * @description 泊车：泊出回调
 * @author heyufei
 * @since 2021/8/3 1:38 下午
 * @version 1.0
 */
sealed class ParkOutCallBack {
    /**
     * 成功执行泊车指令
     */
    object ExecParkOutSuccessful : ParkOutCallBack()

    /**
     * 执行泊车指令失败
     */
//    data class ExecParkOutFailure(val errorOptions: MutableList<IngeekException>) :
//        ParkOutCallBack()


    /**
     * 泊出完成
     */
    object ParkOutComplete : ParkOutCallBack()
}
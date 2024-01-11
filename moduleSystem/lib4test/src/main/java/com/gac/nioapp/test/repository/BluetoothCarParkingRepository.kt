package com.gac.nioapp.test.repository

import androidx.lifecycle.LiveData
import com.syy.modulebase.http.messge.livedata.UnPeekLiveData

/**
 * @className BluetoothCarParkingRepository
 * @description 蓝牙泊车（自动泊车）
 * 泊入：接受车机泊入指令(APP被动开始)
 * 泊出：主动向车机发起泊出请求(APP主动)
 * @author heyufei
 * @since 2021/8/2 5:08 下午
 * @version 1.0
 */
class BluetoothCarParkingRepository private constructor() {
    private val TAG = "BluetoothCarParkingRepository"

    companion object {
        val instance by lazy {
            BluetoothCarParkingRepository()
        }
    }

    private var lastVinCode = ""

//    private var parkManager: IngeekParkManager? = null

    /**
     * 泊入监听
     */
    private val parkInCallBack = UnPeekLiveData<ParkInCallBack>()

    /**
     * 泊出监听
     */
    private val parkOutCallBack = UnPeekLiveData<ParkOutCallBack>()

    /**
     * 泊车过程中监听
     */
    private val parkingCallBack = UnPeekLiveData<ParkingCallBack>()

    /**
     * 泊车前自检
     */
    private val parkSelfCheckCallBack = UnPeekLiveData<ParkSelfCheckCallBack>()

    init {
        VCPCarCommonRepository.instance.getVinLive().observeForever {
            initVinCode(it)
        }
    }

//    private fun initListener() {
//        parkManager!!.setParkListener(object : IngeekParkListener() {
//
//            /**
//             * 收到泊入请求
//             */
//            override fun onReceiveParkIn(direction: Int) {
//                parkInCallBack.postValue(ParkInCallBack.ReceiveParkIn)
//            }
//
//
//            /**
//             * 撤销泊入请求
//             */
//            override fun onRevokeParkIn() {
//                parkInCallBack.postValue(ParkInCallBack.RevokeParkIn)
//            }
//
//            /**
//             * 自检结果
//             */
//            override fun onParkSelfCheckResult(
//                checkResult: Int,
//                errorOptions: MutableList<IngeekException>
//            ) {
//                CommonSimpleLog.w(
//                    TAG,
//                    "自检结果:  $checkResult，${errorOptions.size}, + ${
//                        errorOptions.map { it.getErrorMsg() + ",code:" + it.getErrorCode() }
//                            .toList()
//                    }",
//                    "flyme", CommonSimpleLog.LABEL_INGEEK
//                )
//                if (checkResult == 0) {
//                    //自检通过
//                    parkSelfCheckCallBack.postValue(ParkSelfCheckCallBack.ParkSelfCheckSuccessful)
//                } else {
//                    parkSelfCheckCallBack.postValue(
//                        ParkSelfCheckCallBack.ParkSelfCheckError(
//                            errorOptions
//                        )
//                    )
//                }
//            }
//
//            /**
//             * 泊车暂停
//             */
//            override fun onParkPause(errorOptions: MutableList<IngeekException>) {
//                parkingCallBack.postValue(ParkingCallBack.ParkPause(errorOptions))
//            }
//
//            /**
//             * 暂停恢复
//             */
//            override fun onParkResume() {
//                parkingCallBack.postValue(ParkingCallBack.ParkResume)
//            }
//
//            /**
//             * 泊车失败
//             */
//            override fun onParkStop(errorOptions: MutableList<IngeekException>) {
//                parkingCallBack.postValue(ParkingCallBack.ParkStop(errorOptions))
//            }
//
//
//            /**
//             * 泊入到车位
//             */
//            override fun onParkInComplete() {
//                parkInCallBack.postValue(ParkInCallBack.ParkInCompleteCarStop)
//
//            }
//
//            /**
//             * 泊入完成
//             * 一定会回调，作为泊入完成的标志
//             */
//            override fun onParkInCompleteWarning(warningOptions: MutableList<IngeekParkInWarning>) {
//                parkInCallBack.postValue(ParkInCallBack.ParkInCompleteWarning(warningOptions))
//            }
//
//            /**
//             * 泊出完成
//             */
//            override fun onParkOutComplete() {
//                parkOutCallBack.postValue(ParkOutCallBack.ParkOutComplete)
//
//            }
//
//            /**
//             * 泊出执行结果
//             * errorOptions：失败原因集合
//             * respone：车控响应结果
//             */
//            override fun onStartParkOutResult(
//                result: Boolean,
//                errorOptions: MutableList<IngeekException>,
//                response: IngeekVehicleCommandResponse?
//            ) {
//                if (result) {
//                    //泊车启动成功
//                    parkOutCallBack.postValue(ParkOutCallBack.ExecParkOutSuccessful)
//                } else {
//                    //泊出启动失败
//                    parkOutCallBack.postValue(
//                        ParkOutCallBack.ExecParkOutFailure(
//                            errorOptions
//                        )
//                    )
//
//                }
//            }
//        })
//    }

//    /**
//     * 激活泊入
//     */
//    fun enableParkIn() = parkManager?.enableParkIn()
//
//
//    /**
//     * 开始泊出
//     */
//    fun parkOut() = parkManager?.parkOut()
//
//
//    /**
//     * 设置泊出方向
//     */
//    fun setParkOutDirection(outDirection: ParkOutDirection) =
//        parkManager?.setParkOutDirection(outDirection.direction)
//
//
//    /**
//     * 设置按压事件
//     * state:True
//     * true：代表开始泊车
//     * false：代表暂停泊车
//     */
//    fun startPark(state: Boolean) = parkManager?.setEffectivelyTouching(state)
//
//    /**
//     * 取消泊车
//     */
//    fun cancelPark() = parkManager?.cancelPark()


    /**
     * 泊入结果
     */
    fun parkInResult(): LiveData<ParkInCallBack> = parkInCallBack

    /**
     * 泊出监听
     */
    fun parkOutResult(): LiveData<ParkOutCallBack> = parkOutCallBack

    /**
     * 泊车过程中监听
     */
    fun parkingResult(): LiveData<ParkingCallBack> = parkingCallBack

    /**
     * 泊车前自检
     */
    fun parkSelfCheckResult(): LiveData<ParkSelfCheckCallBack> = parkSelfCheckCallBack

    /**
     * 初始化 vinCode
     */
    fun initVinCode(vinCode: String) {
        if (lastVinCode != vinCode) {
            lastVinCode = vinCode
//            parkManager = IngeekParkManager(
//                IngeekVehicleProperty().apply {
//                    vin = vinCode
//                }
//            )
//
//            initListener()
        }
    }

    fun getVin() = lastVinCode
}
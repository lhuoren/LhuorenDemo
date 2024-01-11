package com.syy.modulebase.utils

import android.os.SystemClock
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/***
 * 时间同步器,这里取的都是与服务器同步的时钟，并非本地用户能有改变的时钟，请尽量少用System.currentTimeMillis()，多用TimeAsyncManager.getCurrentTimeMillis()
 *  create by liming on 2023/04/10
 */
object TimeAsyncManager {
    private var TAG = this.javaClass.simpleName

    @Volatile
    private var elapsedRealtimeDiff: Long? = null

    @Volatile
    private var currentTimeMillisDiff: Long? = null

    @Volatile
    private var currentNtpServer: String? = null

    private var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS")

    /** ntp服务器列表 */
    private var ntpServerList = arrayOf(
        "ntp.aliyun.com",
        "ntp1.aliyun.com",
        "ntp2.aliyun.com",
        "ntp3.aliyun.com",
        "ntp4.aliyun.com",
        "ntp5.aliyun.com",
        "ntp6.aliyun.com",
        "ntp7.aliyun.com"
    )

    private fun asyncTime(ntpUrl: String): Boolean {
        try {
            currentNtpServer = ntpUrl
            var sntpClient = SntpClient()
            if (sntpClient.requestTime(ntpUrl, 3000)) {
                var serverTimeStamp = sntpClient.ntpTime
                if (serverTimeStamp == 0L) {
                    return false
                }
                elapsedRealtimeDiff = serverTimeStamp - SystemClock.elapsedRealtime()
                currentTimeMillisDiff = serverTimeStamp - System.currentTimeMillis()
                Log.e(
                    TAG,
                    "同步服务器时间成功：server:$ntpUrl, elapsedRealtimeDiff：$elapsedRealtimeDiff ,currentTimeMillisDiff->$currentTimeMillisDiff"
                )
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "同步服务器时间失败：server:$ntpUrl,error:${e.message}")
        }
        return false
    }

    /**
     * 当前连接的ntp服务器
     */
    @JvmStatic
    fun getCurrentNtpServer(): String? {
        return currentNtpServer
    }

    /**
     * 开机时间与服务器时间误差
     */
    @JvmStatic
    fun getElapsedRealtimeDiff(): Long? {
        return elapsedRealtimeDiff
    }

    /**
     * 本地时间与服务器时间误差
     */
    @JvmStatic
    fun getCurrentTimeMillisDiff(): Long? {
        return currentTimeMillisDiff
    }

    @JvmStatic
    fun startAsync() {
        Thread(Runnable out@{
            ntpServerList.forEach {
                if (asyncTime(it)) {
                    return@out
                }
            }
        }).start()
    }

    /**
     * 获取当前时间戳
     */
    @JvmStatic
    fun getCurrentTimeMillis(): Long {
        //临时关闭，待定测试之后开启。目前还是使用local的时间。
        var df = elapsedRealtimeDiff ?: return System.currentTimeMillis()
        return SystemClock.elapsedRealtime() + df
    }

    /**
     * 获取日期字符串
     */
    @JvmStatic
    fun getCurrentDate(): String {
        var timeStamp = getCurrentTimeMillis()
        return sdf.format(Date(timeStamp))
    }
}
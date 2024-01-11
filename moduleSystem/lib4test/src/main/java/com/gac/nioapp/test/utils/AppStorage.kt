package com.gac.nioapp.test.utils

import com.gac.nioapp.test.theme.DayNightManager
import com.syy.modulebase.utils.AppInfoManager
import com.syy.modulebase.utils.SharePreferenceUtil
import com.tencent.mmkv.MMKV

/**
 * Created by xiaojiang on 2020-03-31.
 */

object AppStorage {

    private const val CAR_SELECT_NUM = "CAR_SELECT_NUM"
    private const val DARK_MODE = "DARK_MODE"
    private const val SHOW_AGREEMENT = "SHOW_AGREEMENT"

    /**
     * 当前个性选号
     */
    @JvmStatic
    fun setCarSelectNum(number: String) {
        SharePreferenceUtil.put(AppInfoManager.getInstance().getContext(), CAR_SELECT_NUM, number)
    }

    @JvmStatic
    fun getCarSelectNum(): String {
        return SharePreferenceUtil.get(
            AppInfoManager.getInstance().context,
            CAR_SELECT_NUM,
            ""
        ).toString()
    }

    @JvmStatic
    fun setDarkMode(mode: Int) {
        val kv = MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, null)
        kv.encode(DARK_MODE, mode)
    }

    @JvmStatic
    fun getDarkMode(): Int {
        val kv = MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, null)
        val mode = kv.decodeInt(DARK_MODE, DayNightManager.MODE.NO.va)
        return mode
    }

    fun agreeAgreement(): Boolean {
        // 需要适配多进程:https://github.com/Tencent/MMKV/issues/342
        val kv = MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, null)
        val mode = kv.decodeInt(SHOW_AGREEMENT, 0)
        return mode != 0
    }

    fun setAgreeAgreement() {
        val kv = MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, null)
        kv.encode(SHOW_AGREEMENT, 1)
    }

}
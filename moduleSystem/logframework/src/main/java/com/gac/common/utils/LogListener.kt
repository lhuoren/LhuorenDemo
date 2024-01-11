package com.gac.common.utils


interface LogListener {
    fun log_d(
        tag: String,
        msg: String?,
        userName: String,
        labels: Array<out CommonSimpleLog.LABEL>?
    )

    fun log_i(
        tag: String,
        msg: String?,
        userName: String,
        labels: Array<out CommonSimpleLog.LABEL>?
    )

    fun log_w(
        tag: String,
        msg: String?,
        userName: String,
        labels: Array<out CommonSimpleLog.LABEL>?
    )

    fun log_e(
        tag: String,
        msg: String?,
        userName: String,
        labels: Array<out CommonSimpleLog.LABEL>?
    )

    fun log_v(
        tag: String,
        msg: String?,
        userName: String,
        labels: Array<out CommonSimpleLog.LABEL>?
    )

    fun log_a(
        tag: String,
        msg: String?,
        userName: String,
        labels: Array<out CommonSimpleLog.LABEL>?
    )
}
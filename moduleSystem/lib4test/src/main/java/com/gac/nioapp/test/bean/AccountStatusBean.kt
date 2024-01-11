package com.gac.nioapp.test.bean

/**
 * @ClassName AccountStatusBean
 * @Description TODO
 * @Author LiuZhiQiang
 * @Date 2022/6/21 11:38
 */
data class AccountStatusBean(
        val applyReason: String = "",
        val applyStatus: String = "",// 申请状态，待注销:WAIT_CLOSE,已注销:CLOSED,注销失败:FAIL
        val createTime: String = "",
        val failReason: String,
        val id: Int
)

enum class AccountStatusType(val type: String) {
    WAIT_CLOSE("WAIT_CLOSE"),
    CLOSED("CLOSED"),
    FAIL("FAIL")
}
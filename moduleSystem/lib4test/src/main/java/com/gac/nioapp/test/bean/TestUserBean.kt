package com.gac.nioapp.test.bean

/**
 * @ClassName TestUserBean
 * @Description TODO
 * @Author LiuZhiQiang
 * @Date 2021/12/28 10:10
 */
data class TestUserBean(
        val testAccount: String,
        val accountContext: String,
        var isSelect: Boolean = false
)
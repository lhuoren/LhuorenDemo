package com.gac.nioapp.test.bean

/**
 * @package： com.hycan.community.bean
 * @author： Yee
 * @e-mail： chenyi@douples.com
 * @date： 2023/7/4 14:19
 * @describe：
 */
data class OperationsPopupBean(
    val id: Int,
    val jumpUrl: String?,//跳转链接
    val popPicUrl: String,//弹窗图片
    val type: Int?
)


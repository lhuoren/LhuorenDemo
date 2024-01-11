package com.gac.nioapp.test.bean

import com.syy.modulebase.http.bean.UserIconModel

/**
 * Create by xiaojiang on 12/1/20
 */

class SearchResultModel(
    val pgcVoList: List<SearchArticleModel>? = null,
    val ugcVoList: List<SearchActionModel>? = null,
    val memberVoList: List<SearchUserModel>? = null,
    val activityVoList: List<SearchActiveModel>? = null
) {
}

//用户
data class SearchUserModel(
    val userId: Int,
    val carType: Int,
    val type: Int,
    val remark: String,
    val avatar: String,
    val nickname: String,
    val userIconModel: UserIconModel?
) {

}

//PGC
data class SearchArticleModel(
    val id: Int,
    val title: String,
    var brift: String? = null,
    val coverPath: String? = null,
    val pgcType: Int,
    val roomId: String? = null,
    val user: User? = null
) {

}

//UGC
data class SearchActionModel(
    val id: String,
    val content: String? = null,
    val title: String? = null,
    val firstImg: String? = null,
    val type: Int
) {

}

//活动
data class SearchActiveModel(
    val id: Int,
    val activityTitle: String,
    val activityType: Int,
    val activityTag: String,
    val activityBannerUrl: String? = null,
    val activityContent: String,
    val remark: String? = null,
    val jumpType: Int,
    val contentId: Int
) {

}

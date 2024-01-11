package com.gac.nioapp.test.bean

data class UploadPugcRequestModel(
    val title: String,
    val coverImage: String,
    val content: String,
    val id: String?,
    val userIds: List<String>,
    val topicIdList: List<String>,
    val pugcImage: List<String>,
    val pugcVideos: List<String>
)
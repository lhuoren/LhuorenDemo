package com.gac.nioapp.test.bean

data class CollectBean(
        val id: Int,
        val type: Int,
        val contentId: Int,
        val coverImage: String,
        val title: String,
        val content: String,
        val articleStatus: Int,
        val status: Int

)


data class ExperienceRequest(
        val taskType: Int,//任务类型 1=个人资料 2=调研任务
        val projectId: Int,//项目ID
        val taskName: String,//任务名称
        val taskScore: Int,//任务分值
        val taskStatus: Int,//任务状态：0=待完成 1=已完成
        val id: Int, //任务ID
        val h5Url: String //问卷网H5页面URL
)

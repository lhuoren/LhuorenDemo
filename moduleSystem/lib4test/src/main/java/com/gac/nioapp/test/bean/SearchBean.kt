package com.gac.nioapp.test.bean


data class SearchBean(
        val id: Int,
        val coverPath: String,
        val title: String,
        val brift: String,
        val pgcType: Int,
        val roomId: String,
        val user: User

)


data class TopBannerBean(
        val id: String,//id
        val iconStatus:Int,//0 不展示 1 展示
        val bannerImageType: Int,//1 png/jpg ;2gif
        val jumpUrl: JumpUrlContentBean,//跳转链接对象
        val iconImage: String, //icon图片
        val iconBanner: String //icon主题
)

data class floatActivityBean(
        val id: String,//id
        val bannerImageType: Int,//1 png/jpg ;2gif
        val jumpUrl: JumpUrlContentBean,//跳转链接对象
        val bannerImage: String, //icon图片
        val bannerTitle: String //icon主题
)

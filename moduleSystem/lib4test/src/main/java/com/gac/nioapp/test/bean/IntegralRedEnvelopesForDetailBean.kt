package com.gac.nioapp.test.bean

data class IntegralRedEnvelopesForDetailBean(
    val inscribeLanguage: String,//落款语
    val activityDetailType: String,//VIDEO-视频,IMAGE-图片
    val activityDetailUrl: String,//活动详情文件链接
    val blessings: String,//祝福语
    val greetings: String,//问候语
    val headUrl: String,//头像
    val nickName: String,//昵称
    val score: Int,//积分值
    val shareButtonTitle: String,//分享按钮文案
    val shareImageUrl: String,//分享图
    val sharePosterUrl: String,//分享海报背景
    val shareTitle: String,//分享文案
    val themeImageUrl: String,//主题图片
    val wxShareUrl: String,//微信分享链接
    val wxPosterUrl: String//微信太阳码链接
)
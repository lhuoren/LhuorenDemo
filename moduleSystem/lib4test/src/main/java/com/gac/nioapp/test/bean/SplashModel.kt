package com.gac.nioapp.test.bean


class SplashModel(val title: String? = null,
                  val imageUrl: String? = null,
                  val videoUrl: String? = null,
                  val mediaType: Int? = null,//0--非视频 1--视频
                  val jumpContent: JumpUrlContentBean? = null,
                  val times: Int) {
}
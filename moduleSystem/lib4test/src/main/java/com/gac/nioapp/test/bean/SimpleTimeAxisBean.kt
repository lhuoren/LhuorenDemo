package com.gac.nioapp.test.bean

/**
 * @package： com.gac.common.bean
 * @describe：
 * @author： liming
 * @time： 2020/6/4 1:31 PM
 * @e-mail： liming@gac-nio.com
 */
/**
 *  {
"title": "订单已进行",
"status": 2,
"subTitle": "",
"rightText": "04-01"
}
 */
data class SimpleTimeAxisBean(var title: String? = null,
                              var subTitle: String? = null,
                              var rightText: String? = null,
                              var status: Int = 0) {

}


/**
 *    视频地址
 */
data class RedPaperDetail(var imageUrl: String? = null,
                          var videoUrl: String? = null,
                          var wxShareUrl: String? = null) {

}

data class DetailNewYearRp(var imageUrl: String? = null,
                          var videoUrl: String? = null,
                          var score: Int? = null,
                          var seeTimes: Int? = null,
                          var videoPhotoUrl: String? = null,
                          var describe: String? = null,
                          var greeting: String? = null,
                          var weboTitle: String? = null,
                          var wxShareUrl: String? = null) {
}

data class RP(var score: Int? = null, var status: Int = 0) {

}
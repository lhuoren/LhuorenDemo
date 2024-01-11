package com.gac.nioapp.test.bean

/**
 * Created by xiaojiang on 2020/4/30.
 */
class ResponseList<T>(val total: Int,
                      val size: Int,
                      val pages: Int,
                      val current: Int,
                      val records: List<T>?) {

}
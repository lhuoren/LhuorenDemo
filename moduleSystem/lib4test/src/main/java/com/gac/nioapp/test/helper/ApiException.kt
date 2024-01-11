package com.gac.nioapp.test.helper

/**
 * @className ApiException
 * @description TODO
 * @author heyufei
 * @since 2022/7/1 19:55
 * @version 1.0
 */
class ApiException(val errorMsg: String, val errorCode: Int? = -1) : Exception(errorMsg)
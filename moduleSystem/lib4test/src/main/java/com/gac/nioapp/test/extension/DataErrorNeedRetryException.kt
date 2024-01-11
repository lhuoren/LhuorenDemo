package com.gac.nioapp.test.extension

/**
 * @className DataErrorNeedRetry
 * @description 数据加载失败需要重试
 * @author heyufei
 * @since 2022/1/11 3:59 下午
 * @version 1.0
 */
class DataErrorNeedRetryException(msg: String) : Throwable(msg)
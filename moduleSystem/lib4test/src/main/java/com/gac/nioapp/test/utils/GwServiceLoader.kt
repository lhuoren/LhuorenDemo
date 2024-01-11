package com.gac.nioapp.test.utils

import android.annotation.SuppressLint
import java.util.*

/**
 * @className GwServiceLoader
 * @description 加载被AutoService标记的 服务接口
 * @author heyufei
 * @since 5/18/21 2:25 PM
 * @version 1.0
 */
object GwServiceLoader {

    @SuppressLint("NewApi")
    fun <S> load(clazz: Class<S>): MutableIterator<S>? {
        return ServiceLoader.load(clazz).iterator()
    }
}
package com.gac.nioapp.test.extension

import java.text.SimpleDateFormat
import java.util.*


private const val DATE_FORMAT = "yyyy-MM-dd"

private const val DATE_FORMAT_SPACE = "yyyy - MM - dd"

private const val HHMM = "HH:MM"

private const val COMMENT_DATE_TYPE = "MM-dd HH:mm"

private const val MMSS = "mm:ss"


fun Int.toMinSecond(): String {
    val secondCount = this / 1000
    val min = secondCount / 60
    val second = secondCount - (min * 60)
    val minStr = String.format("%02d", min)
    val secondStr = String.format("%02d", second)
    return "$minStr:$secondStr"
}

fun Long?.toYYMMDDSS(): String {
    if (this == null) return ""
    val format = "yyyy-MM-dd HH:mm:ss"

    val sdf = SimpleDateFormat(format)
    val time = sdf.format(Date(this)) //参数时间
    return time
}
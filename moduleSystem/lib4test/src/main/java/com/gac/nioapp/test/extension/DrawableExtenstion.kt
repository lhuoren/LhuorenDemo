package com.gac.nioapp.test.extension

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.DrawableCompat

/**
 * Created by xiaojiang on 2020-03-25.
 */

fun Drawable.tintDrawable(colors: ColorStateList = ColorStateList.valueOf(Color.WHITE)): Drawable {
    val wrappedDrawable = DrawableCompat.wrap(this)
    DrawableCompat.setTintList(wrappedDrawable, colors)
    return wrappedDrawable
}
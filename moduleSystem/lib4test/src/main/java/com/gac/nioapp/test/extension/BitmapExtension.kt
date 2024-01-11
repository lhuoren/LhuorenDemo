package com.gac.nioapp.test.extension

import android.app.Activity
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.Uri
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

/**
 * Created by xiaojiang on 2020-03-25.
 */

fun Bitmap.dealBackground(): Bitmap {
    for (i in 0 until this.getWidth()) {
        for (j in 0 until this.getHeight()) {
            val color = this.getPixel(i, j)
            val R = Color.red(color)
            val G = Color.green(color)
            val B = Color.blue(color)
            if (R > 130 && G > 120 && B > 100) {
                val newColor = Color.argb(0, 255, 255, 255)
                this.setPixel(i, j, newColor)
            }
        }
    }
    return this
}

fun Bitmap.whiteToBlack(): Bitmap {
    val blackBitmap = this.copy(Bitmap.Config.ARGB_8888, true)
    for (i in 0 until blackBitmap.getWidth()) {
        for (j in 0 until blackBitmap.getHeight()) {
            val color = blackBitmap.getPixel(i, j)
            val R = Color.red(color)
            val G = Color.green(color)
            val B = Color.blue(color)
            if (R == 255 && G == 255 && B == 255) {
                val newColor = Color.rgb(0, 0, 0)
                blackBitmap.setPixel(i, j, newColor)
            }
        }
    }
    return blackBitmap
}

fun Drawable.drawableToBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(
            this.intrinsicWidth,
            this.intrinsicHeight,
            Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    this.setBounds(0, 0, this.intrinsicWidth, this.intrinsicHeight)
    this.draw(canvas)
    return bitmap
}


/**
 * 通过uri获取图片并进行压缩
 *
 * @param uri
 */
fun Activity.getBitmapFormUri(uri: Uri): Bitmap? {
    var input = this.contentResolver.openInputStream(uri)
    val onlyBoundsOptions = BitmapFactory.Options()
    onlyBoundsOptions.inJustDecodeBounds = true
    onlyBoundsOptions.inDither = true//optional
    onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888//optional
    BitmapFactory.decodeStream(input, null, onlyBoundsOptions)
    input!!.close()
    val originalWidth = onlyBoundsOptions.outWidth
    val originalHeight = onlyBoundsOptions.outHeight
    if (originalWidth == -1 || originalHeight == -1)
        return null
    //图片分辨率以480x800为标准
    val hh = 800f//这里设置高度为800f
    val ww = 480f//这里设置宽度为480f
    //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
    var be = 1//be=1表示不缩放
    if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
        be = (originalWidth / ww).toInt()
    } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
        be = (originalHeight / hh).toInt()
    }
    if (be <= 0)
        be = 1
    //比例压缩
    val bitmapOptions = BitmapFactory.Options()
    bitmapOptions.inSampleSize = be//设置缩放比例
    bitmapOptions.inDither = true//optional
    bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888//optional
    input = this.contentResolver.openInputStream(uri)
    val bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions)
    input!!.close()

    return bitmap!!.compressImage()
}

/**
 * 质量压缩方法
 */
fun Bitmap.compressImage(): Bitmap? {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, baos)//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
    var options = 100
    while (baos.toByteArray().size / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
        baos.reset()//重置baos即清空baos
        //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
        this.compress(Bitmap.CompressFormat.JPEG, options, baos)//这里压缩options%，把压缩后的数据存放到baos中
        options -= 10//每次都减少10
    }
    val isBm = ByteArrayInputStream(baos.toByteArray())//把压缩后的数据baos存放到ByteArrayInputStream中
    return BitmapFactory.decodeStream(isBm, null, null)
}


fun Bitmap.rotateBitmapByDegree(degree: Int): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(degree.toFloat())
    val newBitmap = Bitmap.createBitmap(this, 0, 0, this.getWidth(), this.getHeight(), matrix, true)
    this.recycle()
    return newBitmap
}

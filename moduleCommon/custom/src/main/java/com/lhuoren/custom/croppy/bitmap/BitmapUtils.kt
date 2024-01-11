package com.lhuoren.custom.croppy.bitmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.exifinterface.media.ExifInterface
import com.lhuoren.custom.croppy.CroppedBitmapData
import com.lhuoren.custom.croppy.extensions.rotateBitmap
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.roundToInt


object BitmapUtils {

    private const val MAX_SIZE = 1024

    fun saveBitmap(croppedBitmapData: CroppedBitmapData, file: File): Completable {
        return Completable.create {
            try {
                FileOutputStream(file).use { out ->
                    croppedBitmapData.croppedBitmap?.compress(Bitmap.CompressFormat.PNG, 100, out)
                    it.onComplete()
                }
            } catch (e: Exception) {
                it.onError(e)
            }

        }
    }

    fun resize(uri: Uri, context: Context): Single<ResizedBitmap> {
        return Single.create {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri), null, options)

            var widthTemp = options.outWidth
            var heightTemp = options.outHeight
            var scale = 1

            while (true) {
                if (widthTemp / 2 < MAX_SIZE || heightTemp / 2 < MAX_SIZE)
                    break
                widthTemp /= 2
                heightTemp /= 2
                scale *= 2
            }

            val resultOptions = BitmapFactory.Options().apply {
                inSampleSize = scale
            }
            var resizedBitmap = BitmapFactory.decodeStream(
                context.contentResolver.openInputStream(uri),
                null,
                resultOptions
            )

            resizedBitmap = resizedBitmap?.rotateBitmap(getOrientation(context.contentResolver.openInputStream(uri)))

            it.onSuccess(ResizedBitmap(resizedBitmap))
        }
    }

    private fun getOrientation(inputStream: InputStream?): Int {
        val exifInterface: ExifInterface
        var orientation = 0
        try {
            exifInterface = ExifInterface(inputStream!!)
            orientation = exifInterface.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return orientation
    }

    /**
     * 根据 路径 得到 file 得到 bitmap
     * @param filePath
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    fun decodeFile(filePath: String?): Bitmap? {
        var bitmap: Bitmap? = null
        val IMAGE_MAX_SIZE = 600
        Log.i("decodeFile","filePath:$filePath")
        val file = File(if(filePath?.contains("file:")!!) filePath.substring(7,filePath.length) else filePath)
        Log.i("decodeFile","absolutePath:${file.absolutePath}")
        //Decode image size
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        var fis = FileInputStream(file)
        BitmapFactory.decodeStream(fis, null, options)
        fis.close()
        var scale = 1
        if (options.outHeight > IMAGE_MAX_SIZE || options.outWidth > IMAGE_MAX_SIZE) {
            scale = 2.0.pow(
                (ln(IMAGE_MAX_SIZE / options.outHeight.coerceAtLeast(options.outWidth).toDouble()) / ln(0.5)).roundToInt().toDouble()
            ).toInt()
        }

        //Decode with inSampleSize
        val o2 = BitmapFactory.Options()
        o2.inSampleSize = scale
        fis = FileInputStream(file)
        bitmap = BitmapFactory.decodeStream(fis, null, o2)
        fis.close()
        return bitmap
    }
}
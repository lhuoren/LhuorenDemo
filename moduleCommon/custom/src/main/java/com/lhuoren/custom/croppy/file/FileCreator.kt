package com.lhuoren.custom.croppy.file

import android.content.Context
import android.os.Environment
import android.util.Log
import com.lhuoren.custom.croppy.StorageType.CACHE
import com.lhuoren.custom.croppy.StorageType.EXTERNAL
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.IOException


object FileCreator {

    fun createFile(fileOperationRequest: FileOperationRequest, context: Context): File {
        return when (fileOperationRequest.storageType) {
            CACHE -> createCacheFile(fileOperationRequest, context)
            EXTERNAL -> createExternalFile(fileOperationRequest, context)
        }
    }

    private fun createCacheFile(
        fileOperationRequest: FileOperationRequest,
        context: Context
    ): File {
        val outputDir = context.cacheDir
        return File.createTempFile(
            "img",
            fileOperationRequest.fileName + fileOperationRequest.fileExtension.fileExtensionName,
            outputDir
        )
    }

    private fun createExternalFile(
        fileOperationRequest: FileOperationRequest,
        context: Context
    ): File {
        val path = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val parentFolder = File(path, "croppy")
            .also { it.mkdirs() }

        return File(
            parentFolder,
            "${fileOperationRequest.fileName}${fileOperationRequest.fileExtension.fileExtensionName}"
        )
    }

    fun readFile(file: File): ByteArray? { // 需要读取的文件，参数是文件的路径名加文件名
        if (file.isFile) { // 以字节流方法读取文件
            var fis: FileInputStream? = null
            try {
                fis = FileInputStream(file) // 设置一个，每次 装载信息的容器
                val buffer = ByteArray(1024)
                val outputStream = ByteArrayOutputStream() // 开始读取数据
                var len = 0 // 每次读取到的数据的长度
                while (fis.read(buffer).also { len = it } != -1) { // len值为-1时，表示没有数据了
                    // append方法往sb对象里面添加数据
                    outputStream.write(buffer, 0, len)
                } // 输出字符串
                return outputStream.toByteArray()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            Log.e("readFile","文件不存在！")
        }
        return null
    }

}
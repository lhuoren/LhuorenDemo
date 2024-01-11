package com.lhuoren.custom.croppy.file

import com.lhuoren.custom.croppy.StorageType

data class FileOperationRequest(
    val storageType: StorageType,
    val fileName: String,
    val fileExtension: FileExtension = FileExtension.PNG
) {

    companion object {
        fun createRandom(): FileOperationRequest {
            return FileOperationRequest(
                StorageType.EXTERNAL,
                "machineWallpaper",
                FileExtension.PNG
            )
        }
    }

}
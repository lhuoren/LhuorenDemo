package com.gac.nioapp.test.bean

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UgcVideoBean(
    val videoUrl: String?,
    val coverImage: String?,
    val width: String?,
    val height: String?
) : Parcelable


@Parcelize
data class UgcPublishBean(
    val ugcContent: String?,
    val topic: List<String>?,
    val userIds: List<String>?,
    val revision: String?,
    @Transient
    val _localFiles: List<Uri>?,
    var ugcVideo: UgcVideoBean? = null,
    @Transient
    var _tempVideoBean: TempVideoBean? = null,
    @Transient
    var _localCompressFiles: String? = null,//压缩后的
    @Transient
    var _userId: String? = null,
) : Parcelable

enum class UgcPublishTaskStatus(val status: String) {
    UPLOADING("uploading"),
    SUCCESS("success"),
    FAIL("fail")
}

@Parcelize
data class UgcPublishTaskStatusBean(
    val taskStatus: UgcPublishTaskStatus,
    val progress: Double,//[0.0,1.0]
) : Parcelable

@Parcelize
data class TempVideoBean(
    val id: String?,
    val localVideoFilePath: String?,
    val localCoverImage: String?,
    val coverWidth: String?,
    val coverHeight: String?
) : Parcelable

package com.gac.nioapp.test.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UgcVideo(
    val videoUrl: String?,
    val coverImage: String?,
    val width: String?,
    val height: String?
) : Parcelable
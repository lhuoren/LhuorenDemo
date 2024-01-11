package com.syy.modulebase.http.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserIconModel(val identityIcons: List<String>?, val medal: String):Parcelable{}

package com.lhuoren.custom.croppy

import android.os.Parcelable
import androidx.annotation.ColorRes
import com.lhuoren.custom.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class CroppyTheme(@ColorRes val accentColor: Int) : Parcelable {

    companion object {
        fun default() = CroppyTheme(R.color.color_AB1C1C1E)
    }
}
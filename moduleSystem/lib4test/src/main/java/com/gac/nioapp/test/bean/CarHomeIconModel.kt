package com.gac.nioapp.test.bean

data class CarHomeIconModel(
        val iconStatus: Int = 0,
        val iconObject: CarIconModel? = null
)


data class SkinModel(
        val theme: Int,
        val changeSkin: Boolean? = false
)

data class CarIconModel(val iconImage: String,
                        val iconBanner: String,
                        val bannerImageType: Int)
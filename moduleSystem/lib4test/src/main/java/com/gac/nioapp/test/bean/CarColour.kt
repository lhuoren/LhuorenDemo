package com.gac.nioapp.test.bean

import java.io.Serializable

data class PicDetail(val picLocation: String, val picPath: String) : Serializable

data class CarColourDetails(val colourCode: String, val picDetail: List<PicDetail>) : Serializable

data class CarColour(val carSeries: String, val carColourDetails: List<CarColourDetails>) :
    Serializable

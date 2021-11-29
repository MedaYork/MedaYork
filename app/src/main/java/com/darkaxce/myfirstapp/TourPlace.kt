package com.darkaxce.myfirstapp

import java.io.Serializable

data class TourPlace(
    val img:String,
    val name:String,
    val description:String,
    val rating:Double,
    val temperature:String,
    val location: String,
    val recommendedPlaces: String
    ): Serializable

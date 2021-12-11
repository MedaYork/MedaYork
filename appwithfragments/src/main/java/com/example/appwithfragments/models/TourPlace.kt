package com.example.appwithfragments.models

import java.io.Serializable

data class TourPlace(
    val id:Int,
    val image:String,
    val name:String,
    val description:String,
    val rating:Double,
    val temperature:String,
    val location: String,
    val recommendedPlaces: String,
    val descriptionShort: String
    ): Serializable

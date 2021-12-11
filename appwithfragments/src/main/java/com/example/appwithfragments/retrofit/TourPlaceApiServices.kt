package com.example.appwithfragments.retrofit

import com.example.appwithfragments.models.TourPlace
import retrofit2.Call
import retrofit2.http.*


interface TourPlaceApiService {

    @GET("/MisionTic/webresources/misionTic/list_all")
    fun getTourPlaces(): Call<ArrayList<TourPlace>>




}
package com.example.appwithfragments.repository

import android.util.Log
import com.android.volley.toolbox.Volley
import com.example.appwithfragments.models.TourPlace
import com.example.appwithfragments.retrofit.TourPlaceApiAdapter
import retrofit2.Call
import retrofit2.Callback

class TourPlaceService {

    fun obtenerDatos():Call<ArrayList<TourPlace>>?{

        var listTourPlace: ArrayList<TourPlace> = ArrayList()
        try {


            //URL
            val url ="http://181.49.136.167:15778/MisionTic/webresources/misionTic/list_all"

            val call = TourPlaceApiAdapter.apiService()?.getTourPlaces()
            //apiInterface.enqueue( Callback<List<Movie>>())

            return  call



        } catch (e: Exception) {
            Log.d("error", e.toString())
            e.printStackTrace()

        }

        return null
    }
}
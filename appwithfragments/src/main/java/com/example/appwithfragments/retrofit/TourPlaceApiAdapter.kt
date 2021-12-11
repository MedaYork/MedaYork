package com.example.appwithfragments.retrofit

import com.example.appwithfragments.retrofit.TourPlaceApiService

import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit

import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor


object TourPlaceApiAdapter {
    private var API_SERVICE: TourPlaceApiService? = null// <-- usamos el log level

    // Creamos un interceptor y le indicamos el log level a usar
    fun apiService() : TourPlaceApiService?
        {

            // Creamos un interceptor y le indicamos el log level a usar
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            // Asociamos el interceptor a las peticiones
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            val baseUrl = "http://181.49.136.167:15778/MisionTic/webresources/misionTic/"
            if (API_SERVICE == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()) // <-- usamos el log level
                    .build()
                API_SERVICE = retrofit.create(TourPlaceApiService::class.java)
            }
            return API_SERVICE
        }
}

package com.example.appwithfragments.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appwithfragments.models.TourPlace
import com.example.appwithfragments.repository.TourPlaceService

class TourPlaceViewModel : ViewModel() {

    companion object{

        val service = TourPlaceService()


    }
}
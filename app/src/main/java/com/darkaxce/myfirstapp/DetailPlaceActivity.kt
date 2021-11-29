package com.darkaxce.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.darkaxce.myfirstapp.databinding.ActivityDetailPlaceBinding
import com.squareup.picasso.Picasso


class DetailPlaceActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailPlaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailPlaceBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        val place: TourPlace = intent.extras?.getSerializable("detailsPlace") as TourPlace

        with(detailBinding){
            namePlace.text= place.name
            Picasso.get().load(place.img).into(imagePlace)
            description.text= place.description
            location.text = place.location
            ratingPlace.rating=place.rating.toString().toFloat()
            temperature.text = place.temperature
            recommendPlace.text = place.recommendedPlaces
        }

    }
}
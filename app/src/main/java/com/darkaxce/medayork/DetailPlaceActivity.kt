package com.darkaxce.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailPlaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_place)

        val name = intent.extras?.getString("name")
        val description = intent.extras?.getString("description")
        val rating = intent.extras?.getDouble("rating")
        val img = intent.extras?.getString("img")
        val temperature = intent.extras?.getString("temperature")
        val location = intent.extras?.getString("location")
        val recommended = intent.extras?.getString("recommended")

        val namePlace : TextView = findViewById(R.id.namePlace)
        val imgPlace : ImageView = findViewById(R.id.imagePlace)
        val descriptionPlace : TextView = findViewById(R.id.description)
        val locationPlace : TextView = findViewById(R.id.location)
        val ratingPlace : RatingBar = findViewById(R.id.ratingPlace)
        val temperaturePlace : TextView = findViewById(R.id.temperature)
        val recommendedPlaces : TextView = findViewById(R.id.recommendPlace)

        namePlace.text=name
        Picasso.get().load(img).into(imgPlace)
        ratingPlace.rating=rating.toString().toFloat()
        locationPlace.text = location
        temperaturePlace.text = temperature
        descriptionPlace.text=description
        recommendedPlaces.text = recommended
    }
}
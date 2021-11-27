package com.darkaxce.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.nio.charset.Charset

class TourPlaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour_place)
        initRecycler()
    }

    private fun initRecycler(){
        val recycler: RecyclerView = findViewById(R.id.listTourPlace)
        recycler.layoutManager=LinearLayoutManager(this)
        val adapter= TourPlaceAdapter(createListTourPLace()){
            val intent = Intent(this,DetailPlaceActivity::class.java).apply{
                putExtra("name",it.name)
                putExtra("description",it.description)
                putExtra("rating",it.rating)
                putExtra("img",it.img)
                putExtra("temperature",it.temperature)
                putExtra("location",it.location)
                putExtra("recommended",it.recommendedPlaces)
            }

            startActivity(intent)
        }
        recycler.adapter=adapter
    }

    private fun getJSONFromAssets() : String?{
        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myListTourPlace = assets.open("list.json")
            val size = myListTourPlace.available()
            val buffer = ByteArray(size)
            myListTourPlace.read(buffer)
            myListTourPlace.close()
            json= String(buffer,charset)
        }catch (ex:IOException){
            ex.printStackTrace()
            return null
        }
        return json
    }

    private fun createListTourPLace() : ArrayList<TourPlace> {
        val listTourPlace : ArrayList<TourPlace> = ArrayList()
        try {
            val placesArray = JSONArray(getJSONFromAssets()!!)
            for (i in 0 until placesArray.length()){
                val placeInfo = placesArray.getJSONObject(i)
                val name = placeInfo.getString("name")
                val description = placeInfo.getString("description")
                val rating = placeInfo.getDouble("rating")
                val img = placeInfo.getString("img")
                val temperature = placeInfo.getString("temperature")
                val location = placeInfo.getString("location")
                val recommendedPlaces = placeInfo.getString("recommendedPlaces")
                val placeDetails= TourPlace(img,name,description,rating,temperature,location,recommendedPlaces)
                listTourPlace.add(placeDetails)
            }
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return listTourPlace
    }
}


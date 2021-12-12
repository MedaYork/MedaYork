package com.example.appwithfragments.listPOI

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appwithfragments.databinding.FragmentListTourPlaceBinding
import com.example.appwithfragments.main.MainActivity
import com.example.appwithfragments.models.TourPlace
import com.example.appwithfragments.retrofit.TourPlaceApiAdapter
import org.json.JSONArray
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import java.io.IOException
import java.nio.charset.Charset


class ListTourPlaceFragment : Fragment() {

    private lateinit var listTourPlaceBinding: FragmentListTourPlaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity?)?.showIcon()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listTourPlaceBinding = FragmentListTourPlaceBinding.inflate(inflater, container, false)
        return listTourPlaceBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initRecycler()
        cargarDatos()
    }

    private fun initRecycler(listPoi : ArrayList<TourPlace>){
        val recycler: RecyclerView = listTourPlaceBinding.listTourPlace
        val listAdapter= TourPlaceAdapter(listPoi, onClick={openDetailPlace(it)})
        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            setHasFixedSize(false)
        }
    }
    private fun openDetailPlace(tourPlace: TourPlace) {
        findNavController().navigate(
            ListTourPlaceFragmentDirections
                .actionListTourPlaceFragmentToDetailTourPlaceFragment(tourPlace))
    }
    private fun getJSONFromAssets() : String?{
        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myListTourPlace = context?.assets?.open("list.json")
            val size = myListTourPlace?.available()
            val buffer = size?.let { ByteArray(it) }
            myListTourPlace?.read(buffer)
            myListTourPlace?.close()
            json= buffer?.let { String(it,charset) }
        }catch (ex: IOException){
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
                val description_short = ""//placeInfo.getString("descriptionShort")
                val rating = placeInfo.getDouble("rating")
                val img = placeInfo.getString("img")
                val temperature = placeInfo.getString("temperature")
                val location = placeInfo.getString("location")
                val recommendedPlaces = placeInfo.getString("recommendedPlaces")
                val placeDetails= TourPlace(id,img,name,description,rating,temperature,location,recommendedPlaces, description_short, 2F,2F)
                listTourPlace.add(placeDetails)
            }
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return listTourPlace
    }

    private fun cargarDatos() {

        val listTourPlace: ArrayList<TourPlace> = ArrayList()
        try {

            val queue = Volley.newRequestQueue(activity)

            //URL
            val url ="http://181.49.136.167:15778/MisionTic/webresources/misionTic/list_all"

            val call = TourPlaceApiAdapter.apiService()?.getTourPlaces()
            //apiInterface.enqueue( Callback<List<Movie>>())
            call?.enqueue( object : Callback<ArrayList<TourPlace>> {
                override fun onResponse(
                    call: Call<ArrayList<TourPlace>>,
                    response: retrofit2.Response<ArrayList<TourPlace>>
                ) {

                    if(response?.body() != null) {
                        //listTourPlace = response.body()
                        initRecycler(response.body() as ArrayList<TourPlace>)
                    }
                }

                override fun onFailure(call: Call<ArrayList<TourPlace>>?, t: Throwable?) {

                }
            })



        } catch (e: Exception) {
            Log.d("error", e.toString())
            e.printStackTrace()

        }
        //return listTourPlace
    }
}
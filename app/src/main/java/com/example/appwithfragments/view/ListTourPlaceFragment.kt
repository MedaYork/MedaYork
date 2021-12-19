package com.example.appwithfragments.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appwithfragments.databinding.FragmentListTourPlaceBinding
import com.example.appwithfragments.repository.TourPlaceAdapter
import com.example.appwithfragments.models.TourPlace
import com.example.appwithfragments.repository.TourPlaceService
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

    private fun initRecycler(listPoi: ArrayList<TourPlace>){
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



    private fun cargarDatos() {

        val service = TourPlaceService()

        val call = service.obtenerDatos()

        call?.enqueue( object : Callback<ArrayList<TourPlace>> {
            override fun onResponse(
                call: Call<ArrayList<TourPlace>>,
                response: retrofit2.Response<ArrayList<TourPlace>>
            ) {

                initRecycler(response.body()!!)
            }

            override fun onFailure(call: Call<ArrayList<TourPlace>>?, t: Throwable?) {

            }
        })

        //return listTourPlace
    }
}
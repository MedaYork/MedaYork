package com.example.appwithfragments.main

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.util.Log
import com.example.appwithfragments.R
import com.google.android.gms.maps.model.BitmapDescriptorFactory


class MapsFragment : Fragment() {

    var name : String = ""
    var latitude : Float = 0.0f
    var longitude : Float = 0.0f

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val poi = LatLng( latitude.toDouble(),longitude.toDouble())


        googleMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).position(poi).title(name))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(poi))
        googleMap.setMinZoomPreference(18.0f)
        googleMap.setMaxZoomPreference(44.0f)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Obtienes el Bundle del Intent
        val datosRecuperados = arguments

        latitude = datosRecuperados?.getFloat("latitud") ?: 0f
        longitude = datosRecuperados?.getFloat("longitud") ?:0f
        name = datosRecuperados?.getString("name").toString()
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Obtienes el Bundle del Intent
        val datosRecuperados = arguments

        latitude = datosRecuperados?.getFloat("latitud") ?: 0f
        longitude = datosRecuperados?.getFloat("longitud") ?:0f
        name = datosRecuperados?.getString("name").toString()
        Log.d("longitud", longitude.toString())
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }
}
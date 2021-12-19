package com.example.appwithfragments.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appwithfragments.R
import com.example.appwithfragments.databinding.FragmentDetailTourPlaceBinding
import com.example.appwithfragments.models.TourPlace
import com.squareup.picasso.Picasso

class DetailTourPlaceFragment : Fragment() {

    private lateinit var detailTourPlaceBinding: FragmentDetailTourPlaceBinding
    private val args: DetailTourPlaceFragmentArgs by navArgs()
    private lateinit var tourPlace : TourPlace

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailTourPlaceBinding= FragmentDetailTourPlaceBinding.inflate(inflater, container, false)
        return detailTourPlaceBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tourPlace = args.detailPlace
        this.tourPlace = tourPlace

        with(detailTourPlaceBinding){

            namePlace.text= tourPlace.name
            Picasso.get().load(tourPlace.image).into(imagePlace)
            description.text= tourPlace.description
            location.text = tourPlace.location
            ratingPlace.rating=tourPlace.rating.toString().toFloat()
            temperature.text = tourPlace.temperature
            recommendPlace.text = tourPlace.recommendedPlaces
        }

        detailTourPlaceBinding.btnMaps.setOnClickListener {
            var fragment : MapsFragment
            var bundle : Bundle
            bundle = Bundle()
            //Log.d("error", this.tourPlace.longitude)
            bundle.putFloat("latitud",this.tourPlace.latitude)
            bundle.putFloat("longitud",this.tourPlace.longitude)
            bundle.putString("name",this.tourPlace.name)
            findNavController().navigate(R.id.mapsFragment,bundle)
        }

    }
}
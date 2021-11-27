package com.darkaxce.medayork

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException

class DetailPlaceActivity : AppCompatActivity() {

    var id : Int = 0
    lateinit var poi : Poi
    lateinit var nombre : TextView
    lateinit var image : ImageView
    lateinit var ubicacion : TextView
    lateinit var ratingBar : RatingBar
    lateinit var temperatureView : TextView
    lateinit var recommendedPlacesView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_place)
        poi = Poi(1,"55555","","","",4F,"","","")
        nombre = findViewById(R.id.comuna13)
        image = findViewById(R.id.imageView2)
        ubicacion = findViewById(R.id.location)
        ratingBar = findViewById(R.id.rating)
        temperatureView = findViewById(R.id.temperature)
        recommendedPlacesView = findViewById(R.id.recommend1)


        id = getIntent().getExtras()?.getInt("id") ?: 0;
        cargarDatos()




    }

    private fun cargarDatos() {
        try {

            val queue = Volley.newRequestQueue(applicationContext)

            //URL
            val url ="http://pruebas.barranquilla.gov.co:15777/MisionTic/webresources/misionTic/list?id="+id

            val progressDialog = ProgressDialog(this@DetailPlaceActivity)
            progressDialog.setTitle("MedaYork")
            progressDialog.setMessage("Application is loading, please wait")
            progressDialog.show()

            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener { response ->
                    try {

                        var id : Int
                        var name : String
                        var img : String
                        var description : String
                        var description_short : String
                        var rating : Float
                        var temperature : String
                        var location : String
                        var recommendedPlaces : String

                        //val lstGerencia = mutableListOf<Sitio>()

                        val dataFormatPreference = JSONArray(response)
                        Log.d("errorruben","antes de cargar")
                        if (dataFormatPreference.length() < 1) {
                            //progressDialog.dismiss()

                            //val builder = AlertDialog.Builder(this@Recaudos_gerencia)

                            /* builder.setIcon(R.drawable.alert).setTitle("No hay informacion para esta consulta")
                                 .setPositiveButton("Aceptar",
                                     DialogInterface.OnClickListener { dialog, which -> Actividad_anterior() })

                             val alertDialog = builder.create()
                             alertDialog.show()*/

                        }

                        for (i in 0 until dataFormatPreference.length()) {
                            id = dataFormatPreference.getJSONObject(i).getInt("id")
                            name = dataFormatPreference.getJSONObject(i).getString("name")
                            img = dataFormatPreference.getJSONObject(i).getString("image")
                            description = dataFormatPreference.getJSONObject(i).getString("description")
                            description_short = dataFormatPreference.getJSONObject(i).getString("descriptionShort")
                            rating = dataFormatPreference.getJSONObject(i).getDouble("rating").toFloat()
                            temperature = dataFormatPreference.getJSONObject(i).getString("temperature")
                            location = dataFormatPreference.getJSONObject(i).getString("location")
                            recommendedPlaces = dataFormatPreference.getJSONObject(i).getString("recommendedPlaces")



                            poi = Poi(id,name,img,description,description_short,rating,temperature,location,recommendedPlaces)

                            progressDialog.dismiss()
                        }



                        nombre.setText(poi.name)
                        Picasso.get().load(poi.img).into(image);
                        ubicacion.setText(poi.location)
                        ratingBar.setRating(poi.rating)
                        recommendedPlacesView.setText(poi.recommendedPlaces)
                        temperatureView.setText(poi.temperature)


                    } catch (e: JSONException) {
                        //Log.d("errorruben", e.message)
                        e.printStackTrace()
                    }

                    // progressDialog.dismiss()
                }, { error ->
                    //progressDialog.dismiss()
                    //Log.d("errorruben", error.message)


                    //val builder = AlertDialog.Builder(this@Recaudos_gerencia)

                    /*builder.setIcon(R.drawable.alert).setTitle("Error al descargar informacion")
                        .setMessage("Por favor intente mas tarde").setPositiveButton("Aceptar",
                            DialogInterface.OnClickListener { dialog, which -> Actividad_anterior() })

                    val alertDialog = builder.create()
                    alertDialog.show()*/
                })

            queue.add(stringRequest)
        } catch (e: Exception) {
            Log.d("errorruben", e.toString())
            e.printStackTrace()

        }

    }
}
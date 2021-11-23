package com.darkaxce.medayork

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import org.json.JSONException



class places : AppCompatActivity() {

    var recyclerPoi: RecyclerView? = null
    var listPoi: ArrayList<Poi>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)
        Log.d("errorruben","antes de cargar")
        listPoi = ArrayList<Poi>()
        recyclerPoi = findViewById(R.id.recyclerId) as RecyclerView

        cargarDatos()
        Log.d("errorruben","antes de cargar2")
    }

    private fun cargarDatos() {
        try {

            val queue = Volley.newRequestQueue(applicationContext)

            //URL
            val url ="http://190.248.57.51:9999/movil/list_poi.php"

            val progressDialog = ProgressDialog(this@places)
            progressDialog.setTitle("MedaYork")
            progressDialog.setMessage("Application is loading, please wait")
            progressDialog.show()

            val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener { response ->
                    try {

                        var id : String
                        var name : String
                        var img : String
                        var description : String
                        var description_short : String
                        var rating : String
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
                            id = dataFormatPreference.getJSONObject(i).getString("id")
                            name = dataFormatPreference.getJSONObject(i).getString("name")
                            img = dataFormatPreference.getJSONObject(i).getString("img")
                            description = dataFormatPreference.getJSONObject(i).getString("description")
                            description_short = dataFormatPreference.getJSONObject(i).getString("description_short")
                            rating = dataFormatPreference.getJSONObject(i).getString("rating")
                            temperature = dataFormatPreference.getJSONObject(i).getString("temperature")
                            location = dataFormatPreference.getJSONObject(i).getString("location")
                            recommendedPlaces = dataFormatPreference.getJSONObject(i).getString("recommendedPlaces")




                            var poi = Poi(id,name,img,description,description_short,rating,temperature,location,recommendedPlaces)
                            listPoi?.add(poi)
                            progressDialog.dismiss()
                        }

                        val myAdapter = RecyclerPoiAdapter(this, listPoi)
                        myAdapter?.setOnClickListener(object : View.OnClickListener {
                            override fun onClick(view: View) {


                                val intent =
                                    Intent(applicationContext, DetailPlaceActivity::class.java)
                                var index = recyclerPoi?.getChildAdapterPosition(view)
                                var id = index?.let { listPoi?.get(it)?.id }
                                intent.putExtra(
                                    "id",
                                            id
                                )
                                Toast.makeText(applicationContext,"this is toast message = "+id, Toast.LENGTH_SHORT).show()
                                startActivity(intent)
                                finish()


                            }
                        })
                        recyclerPoi?.setLayoutManager(GridLayoutManager(this, 1))
                        recyclerPoi?.setHasFixedSize(true)
                        recyclerPoi?.setAdapter(myAdapter)

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
package com.darkaxce.medayork

class Poi {

    val id : String
    val name : String
    val img : String
    val description : String
    val description_short : String
    val rating : String
    val temperature : String
    val location : String
    val recommendedPlaces : String

     constructor(id: String, name:String ,  img:String,  description:String,
                    description_short:String, rating:String, temperature:String, location:String, recommendedPlaces:String ){
        this.id = id
         this.name = name
         this.img = img
         this.description = description
         this.description_short = description_short
         this.rating = rating
         this.temperature = temperature
         this.location = location
         this.recommendedPlaces = recommendedPlaces



    }





}
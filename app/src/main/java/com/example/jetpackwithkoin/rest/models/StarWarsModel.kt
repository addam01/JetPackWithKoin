package com.example.jetpackwithkoin.rest.models

import com.google.gson.annotations.SerializedName

/** Created by addam in 2019-11-01 **/
data class StarWarsModel (
    @SerializedName("stub")
    var stub: String
)

data class Person(
    @SerializedName("name")
    var name: String,
    
    @SerializedName("height")
    var height: String
)

data class Planet(
    @SerializedName("name")
    var name: String
) 

data class Starship(
    @SerializedName("name")
    var name: String,
    @SerializedName("starship_class")
    var starship_class: String
)
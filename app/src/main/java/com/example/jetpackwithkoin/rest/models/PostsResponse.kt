package com.example.jetpackwithkoin.rest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by owner on 17/06/2019
 */
data class PostsResponse (
    @SerializedName("id")
    @Expose
    var id: String,
    
    @SerializedName("title")
    @Expose
    var title: String
)
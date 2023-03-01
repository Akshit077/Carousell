package com.example.carousell.api

import com.google.gson.annotations.SerializedName

class DataModel (

    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title : String,

    @SerializedName("description")
    val description: String,

    @SerializedName("banner_url")
    val bannerUrl: String,

    @SerializedName("time_created")
    val timeCreated: Long,

    @SerializedName("rank")
    val rank:Int
)
package com.example.carousell.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET
    fun getLiveData(): Call<ResponseDataModel>
}
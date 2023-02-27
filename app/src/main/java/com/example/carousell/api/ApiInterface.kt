package com.example.carousell.api

import retrofit2.http.GET

interface ApiInterface {

    @GET
    fun getLiveData():retrofit2.Call<ResponseDataModel>
}
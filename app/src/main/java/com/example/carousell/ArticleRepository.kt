package com.example.carousell

import com.example.carousell.api.RetrofitService

class ArticleRepository constructor(private val retrofitService: RetrofitService) {
        fun getAllNews() = retrofitService.getLiveData()
    }
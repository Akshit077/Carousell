package com.example.carousell

import android.app.ProgressDialog
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carousell.api.DataModel
import com.example.carousell.api.ResponseDataModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ArticlesViewModel constructor(private val repository: ArticleRepository)  : ViewModel() {

    val newsList = MutableLiveData<ArrayList<DataModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllArticles() {
        val response = repository.getAllNews()
        response.enqueue(object : retrofit2.Callback<ArrayList<DataModel>> {
            override fun onResponse(
                call: Call<ArrayList<DataModel>>,
                response: Response<ArrayList<DataModel>>
            ) {
                if (response.isSuccessful) {
                    newsList.postValue(response.body()?: ArrayList())
                    Log.e("Data", "Data is ${response.body()}\n\n")
                }
            }

            override fun onFailure(call: Call<ArrayList<DataModel>>, t: Throwable) {
                // progressDialog.dismiss()
                Log.e("Failure", "Error is ${t.localizedMessage}")
                errorMessage.postValue(t.message)
            }
        })
    }
}
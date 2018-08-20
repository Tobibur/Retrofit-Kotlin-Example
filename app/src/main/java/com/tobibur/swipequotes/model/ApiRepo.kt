package com.tobibur.swipequotes.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.tobibur.swipequotes.model.service.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApiRepo{

    var endpoints : ApiClient = ApiClient()

    fun getPosts() : LiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)
        val call : Call<QuoteModel> = apiService.getQuotes("getQuote","json","en")
        call.enqueue(object : Callback<QuoteModel>{
            override fun onFailure(call: Call<QuoteModel>?, t: Throwable?) {
                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(call: Call<QuoteModel>?, response: Response<QuoteModel>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(ApiResponse(response.body()!!))
                }
            }

        })

        return apiResponse
    }
}
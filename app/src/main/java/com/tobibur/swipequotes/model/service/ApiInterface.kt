package com.tobibur.swipequotes.model.service

import com.tobibur.swipequotes.model.QuoteModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("api/1.0/")
    fun getQuotes(@Query("method")method: String ,@Query("format")format: String,@Query("lang") lang:String ): Call<QuoteModel>
}

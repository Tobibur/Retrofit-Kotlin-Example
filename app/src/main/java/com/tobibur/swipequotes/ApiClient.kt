package com.tobibur.swipequotes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class ApiClient{

    private val BASE_URL = "http://api.forismatic.com/"
    private var retrofit : Retrofit? = null

    public fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }
}
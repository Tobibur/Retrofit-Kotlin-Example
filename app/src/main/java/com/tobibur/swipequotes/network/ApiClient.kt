package com.tobibur.swipequotes.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class ApiClient{

    private val baseURL = "http://api.forismatic.com/"
    private var retrofit : Retrofit? = null

    private var logging = HttpLoggingInterceptor()

    private fun getHttpLogClient() : OkHttpClient{
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }

    public fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpLogClient())
                    .build()
        }
        return retrofit
    }
}
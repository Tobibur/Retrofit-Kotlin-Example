package com.tobibur.swipequotes.model.service

import com.tobibur.swipequotes.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



class ApiClient{

    private val baseURL = "http://api.forismatic.com/"
    private var retrofit : Retrofit? = null

    private var logging = HttpLoggingInterceptor()

    private fun getHttpLogClient() : OkHttpClient{
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getHttpLogClient())
                    .build()
        }
        return retrofit
    }
}
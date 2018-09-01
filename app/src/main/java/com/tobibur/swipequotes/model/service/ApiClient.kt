package com.tobibur.swipequotes.model.service

import com.tobibur.swipequotes.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient{

    private val baseURL = "http://api.forismatic.com/"
    private var retrofit : Retrofit? = null
    private val REQUEST_TIMEOUT = 60

    private var logging = HttpLoggingInterceptor()

    private fun getHttpLogClient(): OkHttpClient {
        val httpClient = OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    fun getClient(): Retrofit? {
        //if(okHttpClient == null) initOkHttp()

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
package com.tobibur.swipequotes

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInstance = ApiClient()

        val apiService = apiInstance.getClient()!!.create(ApiInterface::class.java)
        val call : Call<QuoteModel> = apiService.getQuotes("getQuote","json","en")
        call.enqueue(object : Callback<QuoteModel> {
            override fun onFailure(call: Call<QuoteModel>?, t: Throwable?) {
                printLog("Callback failed")
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<QuoteModel>?, response: Response<QuoteModel>?) {
                val qoute : QuoteModel? = response!!.body()
                quoteTextView.text = qoute!!.quoteText +"\n\n-"+ qoute.quoteAuthor
                printLog("Success")
            }

        })
    }

    fun printLog(msg: String){
        Log.d("MainActivity", msg)
    }
}
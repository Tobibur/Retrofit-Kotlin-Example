package com.tobibur.swipequotes.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tobibur.swipequotes.model.QuoteModel
import com.tobibur.swipequotes.R
import com.tobibur.swipequotes.model.network.ApiClient
import com.tobibur.swipequotes.model.network.ApiInterface
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.tobibur.swipequotes.viewmodel.MainActivityViewModel
import android.arch.lifecycle.ViewModelProviders
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            if(it == null){
                printLog("Handle Error")
            }
            if(it?.error == null){
                val qoute : QuoteModel? = it!!.posts
                quoteTextView.text = qoute?.quoteText + "\n\n-"+ qoute?.quoteAuthor
            }else{
                val e : Throwable = it.error!!
                printLog("Error is " + e.message)
            }
        })


//        val apiInstance = ApiClient()
//
//        val apiService = apiInstance.getClient()!!.create(ApiInterface::class.java)
//        val call : Call<QuoteModel> = apiService.getQuotes("getQuote","json","en")
//        call.enqueue(object : Callback<QuoteModel> {
//            override fun onFailure(call: Call<QuoteModel>?, t: Throwable?) {
//                printLog("Callback failed")
//            }
//
//            @SuppressLint("SetTextI18n")
//            override fun onResponse(call: Call<QuoteModel>?, response: Response<QuoteModel>?) {
//                val qoute : QuoteModel? = response!!.body()
//                quoteTextView.text = qoute!!.quoteText +"\n\n-"+ qoute.quoteAuthor
//                printLog("Success")
//            }
//
//        })
    }

    fun printLog(msg: String){
        Log.d("MainActivity", msg)
    }
}
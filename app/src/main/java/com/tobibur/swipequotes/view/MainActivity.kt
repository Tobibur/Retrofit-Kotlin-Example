package com.tobibur.swipequotes.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tobibur.swipequotes.model.QuoteModel
import com.tobibur.swipequotes.R
import kotlinx.android.synthetic.main.activity_main.*
import com.tobibur.swipequotes.viewmodel.MainActivityViewModel
import android.arch.lifecycle.ViewModelProviders


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData(false)
        fab.setOnClickListener{
            getData(true)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getData(refresh : Boolean) {
        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getQuoteData(refresh).observe(this, Observer {
            if(it == null){
                logInfo("Handle Error")
            }
            if(it?.error == null){
                val qoute : QuoteModel? = it!!.posts
                quoteTextView.text = qoute?.quoteText + "\n\n-"+ qoute?.quoteAuthor
            }else{
                val e : Throwable = it.error!!
                logInfo("Error is " + e.message)
            }
        })
    }

    fun logInfo(msg: String){
        Log.i("MainActivity", msg)
    }
}
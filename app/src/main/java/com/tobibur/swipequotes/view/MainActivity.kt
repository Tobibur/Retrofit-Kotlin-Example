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
import android.content.Context
import android.widget.Toast
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    // Lazy Inject ViewModel
    private val viewModel: MainActivityViewModel by viewModel()

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
        viewModel.getQuoteData(refresh).observe(this, Observer {
            if(it == null){
                logInfo("Handle Error")
            }
            if(it?.error == null){
                if(it?.code==null) {
                    val quote: QuoteModel? = it!!.posts
                    quoteTextView.text = quote?.quoteText + "\n\n-" + quote?.quoteAuthor
                }else{
                    when(it.code!!){
                        404 -> toast("Sorry User not found! :(")
                        else ->{
                            toast("Error! Please try again..")
                        }
                    }
                }
            }else{
                val e : Throwable = it.error!!
                logInfo("Error is " + e.message)
            }
        })
    }

    fun logInfo(msg: String){
        Log.i("MainActivity", msg)
    }

    fun toast(msg: String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }
}
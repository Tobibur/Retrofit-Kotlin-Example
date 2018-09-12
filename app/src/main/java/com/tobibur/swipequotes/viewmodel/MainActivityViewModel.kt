package com.tobibur.swipequotes.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.tobibur.swipequotes.model.ApiRepo
import com.tobibur.swipequotes.model.ApiResponse

class MainActivityViewModel(private val mApiRepo: ApiRepo) : ViewModel(){

    private var quote : LiveData<ApiResponse>? = null

    fun getQuoteData(refresh: Boolean): LiveData<ApiResponse>{
        if(refresh){
            quote = null
        }
        if (this.quote == null) {
            quote = mApiRepo.getPosts()
            return quote as LiveData<ApiResponse>
        }
        return quote as LiveData<ApiResponse>
    }
}
package com.tobibur.swipequotes.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.tobibur.swipequotes.model.ApiRepo
import com.tobibur.swipequotes.model.ApiResponse
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer

class MainActivityViewModel : ViewModel(){

    private val mApiResponse: MediatorLiveData<ApiResponse> = MediatorLiveData()
    private val mApiRepo: ApiRepo = ApiRepo()

    fun getData(): LiveData<ApiResponse>{
        mApiResponse.addSource(mApiRepo.getPosts(), Observer<ApiResponse>(){
            mApiResponse.value = it
        })

        return mApiResponse
    }
}
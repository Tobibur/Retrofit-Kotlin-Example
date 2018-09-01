package com.tobibur.swipequotes.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.tobibur.swipequotes.model.service.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApiRepo{

    private var disposables: CompositeDisposable = CompositeDisposable()
    private val apiService = ApiClient().getClient()!!.create(ApiInterface::class.java)
    fun getPosts() : LiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()

        disposables.add(
        apiService.getQuotes("getQuote","json","en")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2)
                .subscribeWith(object : DisposableObserver<QuoteModel>(){
                    override fun onComplete() {

                    }

                    override fun onNext(t: QuoteModel) {
                        apiResponse.postValue(ApiResponse(t))
                    }

                    override fun onError(e: Throwable) {
                        apiResponse.postValue(ApiResponse(e))
                    }

                })
        )

        return apiResponse
    }

    fun clearDisposable(){
        disposables.dispose()
    }
}
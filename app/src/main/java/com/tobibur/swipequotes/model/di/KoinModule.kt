package com.tobibur.swipequotes.model.di

import com.tobibur.swipequotes.model.ApiRepo
import com.tobibur.swipequotes.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    // single instance of HelloRepository
    single { ApiRepo() }

    // MyViewModel ViewModel
    viewModel{MainActivityViewModel(get())}
}
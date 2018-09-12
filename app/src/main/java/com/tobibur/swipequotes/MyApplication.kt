package com.tobibur.swipequotes

import android.app.Application
import com.tobibur.swipequotes.model.di.appModule
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ViewPump.init(ViewPump.builder()
                .addInterceptor(CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Comfortaa-Regular.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build())
        //....

        // Start Koin
        startKoin(this, listOf(appModule))
    }

}
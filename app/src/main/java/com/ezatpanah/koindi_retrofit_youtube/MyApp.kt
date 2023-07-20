package com.ezatpanah.koindi_retrofit_youtube

import android.app.Application
import com.ezatpanah.koindi_retrofit_youtube.di.apiModule
import com.ezatpanah.koindi_retrofit_youtube.di.photoModule
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            /** Retrofit **/
            modules(apiModule, photoModule)
        }
    }

}
package com.example.flickrpoc

import android.app.Application
import com.example.flickrpoc.di.AppComponent
import com.example.flickrpoc.di.AppModule
import com.example.flickrpoc.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}

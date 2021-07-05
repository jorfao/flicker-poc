package com.example.flickrpoc.di

import com.example.flickrpoc.App
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: App)
}

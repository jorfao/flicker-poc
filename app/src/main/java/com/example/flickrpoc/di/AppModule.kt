package com.example.flickrpoc.di

import com.example.flickrpoc.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {
    @Provides
    @Singleton
    fun provideApp() = app
}

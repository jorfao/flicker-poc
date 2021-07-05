package com.example.flickrpoc.di

import com.example.flickrpoc.network.FlickrAPI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FlickrAPIModule(private val flickrAPI: FlickrAPI) {
    @Provides
    @Singleton
    fun providesFlickrAPI() = flickrAPI
}

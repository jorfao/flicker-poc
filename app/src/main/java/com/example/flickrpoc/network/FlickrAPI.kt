package com.example.flickrpoc.network

import androidx.lifecycle.LiveData
import com.example.flickrpoc.BuildConfig
import com.example.flickrpoc.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrAPI {
    @GET("/services/rest/?method=flickr.photos.getRecent&format=json&nojsoncallback=1")
    fun getRecent(@Query("api_key") apiKey: String = BuildConfig.API_KEY): LiveData<ApiResponse<List<Photo>>>

    @GET("/services/rest/?method=flickr.photos.getPopular&format=json&nojsoncallback=1")
    fun getPopular(@Query("api_key") apiKey: String = BuildConfig.API_KEY): LiveData<ApiResponse<List<Photo>>>

    @GET("/services/rest/?method=flickr.photos.getPopular&format=json&nojsoncallback=1")
    fun search(@Query("api_key") apiKey: String = BuildConfig.API_KEY): LiveData<ApiResponse<List<Photo>>>
}

package com.example.flickrpoc.network

import androidx.lifecycle.LiveData
import com.example.flickrpoc.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrAPI {
    @GET("/services/rest/?method=flickr.photos.getRecent&format=json&nojsoncallback=1")
    fun getRecent(@Query("api_key") apiKey: String = BuildConfig.API_KEY, @Query("extras") tag: String): LiveData<ApiResponse<TagPhotosDTO>>

    @GET("/services/rest/?method=flickr.photos.getRecent&format=json&nojsoncallback=1")
    fun getRecent(@Query("api_key") apiKey: String = BuildConfig.API_KEY, @Query("extras") tag: String, @Query("page") page: Int = 0): Call<TagPhotosDTO>

    @GET("/services/rest/?method=flickr.tags.getHotList&format=json&nojsoncallback=1&count=7")
    fun getHotList(@Query("api_key") apiKey: String = BuildConfig.API_KEY): LiveData<ApiResponse<HotListDTO>>

    @GET("/services/rest/?method=flickr.photos.getPopular&format=json&nojsoncallback=1")
    fun search(@Query("api_key") apiKey: String = BuildConfig.API_KEY): LiveData<ApiResponse<List<PhotoDTO>>>

    @GET("/services/rest/?method=flickr.photos.getExif&format=json&nojsoncallback=1")
    fun getPhotoExif(@Query("api_key") apiKey: String = BuildConfig.API_KEY, @Query("photo_id") photoId: String): LiveData<ApiResponse<PhotoDetailsDTO?>>
}

package com.example.flickrpoc.repository

import androidx.lifecycle.LiveData
import com.example.flickrpoc.utils.AppExecutors
import com.example.flickrpoc.BuildConfig
import com.example.flickrpoc.db.PhotoDAO
import com.example.flickrpoc.model.Photo
import com.example.flickrpoc.network.ApiResponse
import com.example.flickrpoc.network.FlickrAPI
import com.example.flickrpoc.network.PhotoDTO
import com.example.flickrpoc.network.Resource
import com.example.flickrpoc.utils.Extensions.toPhoto
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val flickrApi: FlickrAPI,
    private val photoDAO: PhotoDAO,
) {
    fun getRecentPhotos(): LiveData<Resource<List<Photo>>> {
        return object : NetworkBoundResource<List<Photo>, List<PhotoDTO>>(appExecutors) {
            override fun saveCallResult(photos: List<PhotoDTO>) = photoDAO.insertPhotos(photos.map { photoDTO -> photoDTO.toPhoto() })

            override fun shouldFetch(data: List<Photo>?) = data == null

            override fun loadFromDb() = photoDAO.getPhotos()

            override fun createCall(): LiveData<ApiResponse<List<PhotoDTO>>> = flickrApi.getRecent(BuildConfig.API_KEY)
        }.asLiveData()
    }
}

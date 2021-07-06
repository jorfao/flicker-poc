package com.example.flickrpoc.repository

import androidx.lifecycle.LiveData
import com.example.flickrpoc.AppExecutors
import com.example.flickrpoc.db.PhotoDAO
import com.example.flickrpoc.model.Photo
import com.example.flickrpoc.network.FlickrAPI
import com.example.flickrpoc.network.Resource
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val flickrApi: FlickrAPI,
    private val photoDAO: PhotoDAO,
) {
    fun getRecentPhotos(login: String): LiveData<Resource<List<Photo>>> {
        return object : NetworkBoundResource<List<Photo>, List<Photo>>(appExecutors) {
            override fun saveCallResult(photos: List<Photo>) {
                photoDAO.insertPhotos(photos)
            }

            override fun shouldFetch(data: List<Photo>?) = data == null

            override fun loadFromDb() = photoDAO.getPhotos()

            override fun createCall() = flickrApi.getRecent(login)
        }.asLiveData()
    }
}

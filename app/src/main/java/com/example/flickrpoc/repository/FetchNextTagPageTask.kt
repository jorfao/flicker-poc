package com.example.flickrpoc.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flickrpoc.db.FlickrDB
import com.example.flickrpoc.model.TagPage
import com.example.flickrpoc.network.ApiEmptyResponse
import com.example.flickrpoc.network.ApiErrorResponse
import com.example.flickrpoc.network.ApiResponse
import com.example.flickrpoc.network.ApiSuccessResponse
import com.example.flickrpoc.network.FlickrAPI
import com.example.flickrpoc.network.Resource
import java.io.IOException

/**
 * A task that reads the search result in the database and fetches the next page, if it has one.
 */
class FetchNextTagPageTask constructor(
    private val tag: String,
    private val flickrAPI: FlickrAPI,
    private val db: FlickrDB
) : Runnable {
    private val _liveData = MutableLiveData<Resource<Boolean>>()
    val liveData: LiveData<Resource<Boolean>> = _liveData

    override fun run() {
        val current = db.tagDAO().findTagPage(tag)
        if (current == null) {
            _liveData.postValue(null)
            return
        }
        val nextPage = current.page + 1

        if (nextPage > current.pages) {
            _liveData.postValue(Resource.success(false))
            return
        }
        val newValue = try {
            val response = flickrAPI.getRecent(tag = tag, page = nextPage).execute()

            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    // we merge all repo ids into 1 list so that it is easier to fetch the
                    // result list.
                    val parsedPhotos = apiResponse.body.photos.photo.map { it.toPhoto(tag) }
                    val photos = arrayListOf<String>()
                    photos.addAll(current.photos)
                    photos.addAll(parsedPhotos.map { it.id })

                    val merged = TagPage(
                        current.tag,
                        apiResponse.body.photos.page,
                        apiResponse.body.photos.pages,
                        apiResponse.body.photos.perpage,
                        apiResponse.body.photos.total,
                        photos
                    )

                    db.runInTransaction {
                        db.tagDAO().insert(merged)
                        db.photoDAO().insertPhotos(parsedPhotos)
                    }
                    Resource.success(true)
                }
                is ApiEmptyResponse -> {
                    Resource.success(false)
                }
                is ApiErrorResponse -> {
                    Resource.error(apiResponse.errorMessage, true)
                }
            }
        } catch (e: IOException) {
            Resource.error(e.message!!, true)
        }
        _liveData.postValue(newValue)
    }
}

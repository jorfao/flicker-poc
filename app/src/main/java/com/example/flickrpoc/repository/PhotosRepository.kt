package com.example.flickrpoc.repository

import androidx.lifecycle.LiveData
import com.example.flickrpoc.BuildConfig
import com.example.flickrpoc.db.PhotoDAO
import com.example.flickrpoc.db.TagDAO
import com.example.flickrpoc.model.Photo
import com.example.flickrpoc.model.Tag
import com.example.flickrpoc.network.ApiResponse
import com.example.flickrpoc.network.FlickrAPI
import com.example.flickrpoc.network.HotListDTO
import com.example.flickrpoc.network.PhotoDTO
import com.example.flickrpoc.network.Resource
import com.example.flickrpoc.utils.AppExecutors
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val flickrApi: FlickrAPI,
    private val tagDAO: TagDAO,
    private val photoDAO: PhotoDAO,
) {
    val hotListTags by lazy {
        object : NetworkBoundResource<List<Tag>, HotListDTO>(appExecutors) {
            override fun saveCallResult(tags: HotListDTO) = tagDAO.insertTags(tags.hottags.tag.map { tag -> tag.toTag() })

            override fun shouldFetch(data: List<Tag>?) = data == null || data.isEmpty()

            override fun loadFromDb() = tagDAO.getTags()

            override fun createCall(): LiveData<ApiResponse<HotListDTO>> = flickrApi.getHotList()
        }
    }

    fun getRecentPhotosForTag(tag: String): LiveData<Resource<List<Photo>>> {
        return object : NetworkBoundResource<List<Photo>, List<PhotoDTO>>(appExecutors) {
            override fun saveCallResult(photos: List<PhotoDTO>) = photoDAO.insertPhotos(photos.map { photoDTO -> photoDTO.toPhoto() })

            override fun shouldFetch(data: List<Photo>?) = data == null || data.isEmpty()

            override fun loadFromDb() = photoDAO.getPhotos()

            override fun createCall(): LiveData<ApiResponse<List<PhotoDTO>>> = flickrApi.getRecent(BuildConfig.API_KEY)
        }.asLiveData()
    }
}

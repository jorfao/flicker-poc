package com.example.flickrpoc.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.flickrpoc.db.FlickrDB
import com.example.flickrpoc.db.PhotoDAO
import com.example.flickrpoc.db.TagDAO
import com.example.flickrpoc.model.PhotoDetails
import com.example.flickrpoc.model.Tag
import com.example.flickrpoc.model.TagPage
import com.example.flickrpoc.network.ApiResponse
import com.example.flickrpoc.network.FlickrAPI
import com.example.flickrpoc.network.HotListDTO
import com.example.flickrpoc.network.PhotoDetailsDTO
import com.example.flickrpoc.network.Resource
import com.example.flickrpoc.network.TagPhotosDTO
import com.example.flickrpoc.ui.list.TagItemWrapper
import com.example.flickrpoc.utils.AppExecutors
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val flickrApi: FlickrAPI,
    private val flickerDB: FlickrDB,
    private val tagDAO: TagDAO,
    private val photoDAO: PhotoDAO,
) {
    val hotListTags by lazy {
        object : NetworkBoundResource<List<Tag>, HotListDTO>(appExecutors) {
            override fun saveCallResult(tags: HotListDTO) = flickerDB.tagDAO().insertTags(tags.hottags.tag.map { tag -> tag.toTag() })

            override fun shouldFetch(data: List<Tag>?) = data == null || data.isEmpty()

            override fun loadFromDb() = flickerDB.tagDAO().getTags()

            override fun createCall(): LiveData<ApiResponse<HotListDTO>> = flickrApi.getHotList()
        }
    }

    fun getRecentPhotosForTag(tag: String): LiveData<Resource<TagItemWrapper>> {
        return object : NetworkBoundResource<TagItemWrapper, TagPhotosDTO>(appExecutors) {
            override fun saveCallResult(tagPage: TagPhotosDTO) {
                flickerDB.runInTransaction {
                    tagDAO.insert(
                        TagPage(
                            tag, tagPage.photos.page,
                            tagPage.photos.pages, tagPage.photos.perpage, tagPage.photos.total,
                            tagPage.photos.photo.map { photoDTO -> photoDTO.id }
                        )
                    )
                    photoDAO.insertPhotos(tagPage.photos.photo.map { photo -> photo.toPhoto(tag) })
                }
            }

            override fun shouldFetch(data: TagItemWrapper?) = data == null || data.photos.isEmpty()

            override fun loadFromDb() = Transformations.switchMap(photoDAO.getPhotos(tag)) {
                MutableLiveData(TagItemWrapper(tag, it?.toMutableList() ?: mutableListOf()))
            }

            override fun createCall(): LiveData<ApiResponse<TagPhotosDTO>> = flickrApi.getRecent(tag = tag)
        }.asLiveData()
    }

    fun getNextTagPage(tag: String): LiveData<Resource<Boolean>> {
        val fetchNextSearchPageTask = FetchNextTagPageTask(
            tag,
            flickrApi,
            flickerDB
        )
        appExecutors.networkIO().execute(fetchNextSearchPageTask)
        return fetchNextSearchPageTask.liveData
    }

    fun getPhotoDetails(photoId: String) =
        object : NetworkBoundResource<PhotoDetails, PhotoDetailsDTO>(appExecutors) {
            override fun saveCallResult(details: PhotoDetailsDTO) = flickerDB.photoDAO().insertPhotoDetails(details.toPhotoDetails())

            override fun shouldFetch(data: PhotoDetails?) = data == null

            override fun loadFromDb() = flickerDB.photoDAO().getPhotoDetails(photoId)

            override fun createCall(): LiveData<ApiResponse<PhotoDetailsDTO>> = flickrApi.getPhotoExif(photoId = photoId)
        }.asLiveData()
}

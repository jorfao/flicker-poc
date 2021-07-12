package com.example.flickrpoc.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.flickrpoc.model.Tag
import com.example.flickrpoc.network.Resource
import com.example.flickrpoc.network.Status
import com.example.flickrpoc.repository.NextTagPageHandler
import com.example.flickrpoc.repository.PhotosRepository
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

class ListViewModel @Inject constructor(private val photosRepository: PhotosRepository) : ViewModel() {

    private val tagItemWrapperSet = ConcurrentHashMap.newKeySet<TagItemWrapper>()

    private var _results = photosRepository.hotListTags
    val results: LiveData<Resource<List<Tag>>> = _results.asLiveData()

    private val _listResult = MediatorLiveData<Resource<List<TagItemWrapper>>>()
    val listResult = _listResult

    private val tagPhotosHandlerMap: ConcurrentHashMap<String, NextTagPageHandler> = ConcurrentHashMap()

    fun refreshTags() {
        _results.fetchResource(true)
    }

    fun getTagPhotos() {
        _results.asLiveData().value?.data?.forEach {
            _listResult.addSource(photosRepository.getRecentPhotosForTag(it.name)) { result ->
                if (result.status == Status.SUCCESS && result.data != null) {
                    val tagPhotos = result.data!!

                    val tagItemWrapper = tagItemWrapperSet.find { item -> item.tagName == tagPhotos.tagName }
                        ?.apply { this.photos = tagPhotos.photos } ?: tagPhotos

                    tagItemWrapperSet.add(tagItemWrapper)
                    listResult.postValue(Resource(result.status, tagItemWrapperSet.toList(), ""))
                }
            }
        }
    }

    // TODO add paging 
    fun getNextPhotosForTag(tag: String) {
        var handler = tagPhotosHandlerMap[tag]

        if (handler == null) {
            handler = NextTagPageHandler(photosRepository)
            tagPhotosHandlerMap[tag] = handler
        }

        handler.queryNextPage(tag)
    }

    fun getNextPageLoadMoreStatus(tag: String) = tagPhotosHandlerMap[tag]?.loadMoreState
}

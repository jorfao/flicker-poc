package com.example.flickrpoc.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.flickrpoc.model.Tag
import com.example.flickrpoc.network.Resource
import com.example.flickrpoc.repository.NextTagPageHandler
import com.example.flickrpoc.repository.PhotosRepository
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

class ListViewModel @Inject constructor(private val photosRepository: PhotosRepository) : ViewModel() {

    private var _results = photosRepository.hotListTags
    val results: LiveData<Resource<List<Tag>>> = _results.asLiveData()

    private val tagPhotosHandlerMap: ConcurrentHashMap<String, NextTagPageHandler> = ConcurrentHashMap()

    fun refreshTags() {
        _results.fetchResource(true)
    }

    fun getPhotosForTag(tag: String) = photosRepository.getRecentPhotosForTag(tag)

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

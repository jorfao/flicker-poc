package com.example.flickrpoc.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.flickrpoc.model.Tag
import com.example.flickrpoc.network.Resource
import com.example.flickrpoc.repository.PhotosRepository
import javax.inject.Inject

class ListViewModel @Inject constructor(private val photosRepository: PhotosRepository) : ViewModel() {

    private var _results = photosRepository.hotListTags
    val results: LiveData<Resource<List<Tag>>> = _results.asLiveData()

    fun refreshTags() {
        _results.fetchResource(true)
    }
}

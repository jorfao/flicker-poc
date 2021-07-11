package com.example.flickrpoc.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.flickrpoc.model.Tag
import com.example.flickrpoc.network.Resource
import com.example.flickrpoc.repository.PhotosRepository
import javax.inject.Inject

class ListViewModel @Inject constructor(photosRepository: PhotosRepository) : ViewModel() {

    val results: LiveData<Resource<List<Tag>>> = photosRepository.getHotListTags()

}

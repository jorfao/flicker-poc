package com.example.flickrpoc.ui.list

import androidx.lifecycle.ViewModel
import com.example.flickrpoc.repository.PhotosRepository
import javax.inject.Inject

class TagItemViewModel @Inject constructor(private val photosRepository: PhotosRepository) : ViewModel() {

    fun setTagValue(tag: String) {
    }
}

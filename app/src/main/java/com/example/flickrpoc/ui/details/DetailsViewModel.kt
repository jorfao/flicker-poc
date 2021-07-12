package com.example.flickrpoc.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.flickrpoc.model.PhotoDetails
import com.example.flickrpoc.network.Resource
import com.example.flickrpoc.repository.PhotosRepository
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val photosRepository: PhotosRepository) : ViewModel() {
    private val _photoId = MutableLiveData<String>()

    val details: LiveData<Resource<PhotoDetails>> = Transformations.switchMap(_photoId) { photoId ->
        photosRepository.getPhotoDetails(photoId)
    }

    fun setPhotoId(photoId: String) {
        if (_photoId.value != photoId) {
            _photoId.value = photoId
        }
    }
}

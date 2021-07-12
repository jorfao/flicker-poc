package com.example.flickrpoc.network

import com.example.flickrpoc.model.PhotoDetails
import com.example.flickrpoc.utils.Values
import com.google.gson.annotations.SerializedName

class PhotoDetailsDTO(
    @SerializedName("photo") val photo: PhotoDTO,
    @SerializedName("stat") val stat: String,
) {
    fun toPhotoDetails() =
        PhotoDetails(
            this.photo.id, this.photo.title ?: "", Values.IMAGE_URL.format(this.photo.farm, this.photo.server, this.photo.id, this.photo.secret), "",
            this.photo.exif?.map { Pair(it.label, it.raw._content) } ?: emptyList()
        )
}

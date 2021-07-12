package com.example.flickrpoc.network

import com.example.flickrpoc.model.Photo
import com.example.flickrpoc.utils.Values.IMAGE_URL
import com.google.gson.annotations.SerializedName

class PhotoDTO(
    @SerializedName("id")
    val id: String = "",

    @SerializedName("owner")
    val owner: String = "",

    @SerializedName("secret")
    val secret: String = "",

    @SerializedName("server")
    val server: String = "",

    @SerializedName("farm")
    val farm: Int = 0,

    @SerializedName("title")
    val title: String? = "",

    @SerializedName("ispublic")
    val ispublic: Int = 0,

    @SerializedName("isfriend")
    val isfriend: Int = 0,

    @SerializedName("isfamily")
    val isfamily: Int = 0,

    @SerializedName("exif") val exif: List<ExifDTO>?
) {
    fun toPhoto(tagName: String = "") =
        Photo(this.id, this.owner, this.secret, this.server, this.farm, this.title ?: "", IMAGE_URL.format(this.farm, this.server, this.id, this.secret), tagName)
}

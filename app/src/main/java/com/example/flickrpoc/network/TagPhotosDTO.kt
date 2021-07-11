package com.example.flickrpoc.network

import com.google.gson.annotations.SerializedName

class TagPhotosDTO {
    @SerializedName("photos")
    val photos: Photos = Photos()

    class Photos {
        @SerializedName("page")
        val page: Int = 0

        @SerializedName("pages")
        val pages: Int = 0

        @SerializedName("perpage")
        val perpage: Int = 0

        @SerializedName("total")
        val total: Int = 0

        @SerializedName("photo")
        val photo: List<PhotoDTO> = emptyList()
    }
}

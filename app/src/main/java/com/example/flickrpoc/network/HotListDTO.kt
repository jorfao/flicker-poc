package com.example.flickrpoc.network

import com.google.gson.annotations.SerializedName

class HotListDTO {
    @SerializedName("period")
    val period: String = ""

    @SerializedName("count")
    val count: Int = 0

    @SerializedName("hottags")
    val hottags: Hottags = Hottags()

    @SerializedName("stat")
    val stat: String = ""

    data class Hottags(
        @SerializedName("tag")
        val tag: List<Tag> = emptyList()
    )

    class Tag(
        @SerializedName("_content")
        val _content: String = "",

        @SerializedName("thm_data")
        val thm_data: Thm_data,
    ) {
        fun toTag() = com.example.flickrpoc.model.Tag(this._content)
    }

    data class Thm_data(
        @SerializedName("photos")
        val photos: Photos,
    )

    data class Photos(
        @SerializedName("photo")
        val photos: List<PhotoDTO> = emptyList(),
    )
}

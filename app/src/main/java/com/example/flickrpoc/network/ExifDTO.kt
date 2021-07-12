package com.example.flickrpoc.network

import com.google.gson.annotations.SerializedName

data class ExifDTO(
    @SerializedName("tagspace") val tagspace: String,
    @SerializedName("tagspaceid") val tagspaceid: Int,
    @SerializedName("tag") val tag: String,
    @SerializedName("label") val label: String,
    @SerializedName("raw") val raw: RawDTO
)

package com.example.flickrpoc.network

import com.google.gson.annotations.SerializedName

data class RawDTO(

    @SerializedName("_content") val _content: String
)

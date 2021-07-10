package com.example.flickrpoc.network

import com.google.gson.annotations.SerializedName

data class PhotoDTO(
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
    val title: String = "",
)

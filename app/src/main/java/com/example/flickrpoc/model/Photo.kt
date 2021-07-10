package com.example.flickrpoc.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class Photo(
    @field:SerializedName("id")
    val id: String = "",

    @field:SerializedName("owner")
    val owner: String = "",

    @field:SerializedName("secret")
    val secret: String = "",

    @field:SerializedName("server")
    val server: String = "",

    @field:SerializedName("farm")
    val farm: Int = 0,

    @field:SerializedName("title")
    val title: String = "",

    @field:SerializedName("ulr")
    val url: String = "",
)

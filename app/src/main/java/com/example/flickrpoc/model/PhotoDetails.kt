package com.example.flickrpoc.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class PhotoDetails(
    @field:SerializedName("id")
    val id: String = "",
    @field:SerializedName("title")
    val title: String = "",

    @field:SerializedName("url")
    val url: String = "",

    @field:SerializedName("tag")
    val tag: String = "",

    @field:SerializedName("exif")
    val exif: List<Pair<String, String>> = emptyList(),
)

package com.example.flickrpoc.model

import androidx.room.Entity

@Entity(primaryKeys = ["tag"])
data class TagPage(
    val tag: String,
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: Int = 0,
    val photos: List<String>,
)

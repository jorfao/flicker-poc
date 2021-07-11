package com.example.flickrpoc.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["name"])
data class Tag(
    @field:SerializedName("name")
    val name: String = "",
)

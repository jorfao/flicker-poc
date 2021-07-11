package com.example.flickrpoc.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flickrpoc.model.Photo
import com.example.flickrpoc.model.Tag

/**
 * Main database description.
 */
@Database(
    entities = [Photo::class, Tag::class],
    version = 3,
    exportSchema = false
)
abstract class FlickrDB : RoomDatabase() {

    abstract fun photoDAO(): PhotoDAO

    abstract fun tagDAO(): TagDAO
}

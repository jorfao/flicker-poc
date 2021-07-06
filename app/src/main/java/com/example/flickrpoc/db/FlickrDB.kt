package com.example.flickrpoc.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flickrpoc.model.Photo

/**
 * Main database description.
 */
@Database(
    entities = [
        Photo::class
    ],
    version = 3,
    exportSchema = false
)
abstract class FlickrDB : RoomDatabase() {

    abstract fun photoDAO(): PhotoDAO
}

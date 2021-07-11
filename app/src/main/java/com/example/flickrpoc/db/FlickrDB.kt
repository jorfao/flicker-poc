package com.example.flickrpoc.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.flickrpoc.model.Photo
import com.example.flickrpoc.model.Tag
import com.example.flickrpoc.model.TagPage

@Database(
    entities = [Photo::class, Tag::class, TagPage::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(FlickrTypeConverter::class)
abstract class FlickrDB : RoomDatabase() {

    abstract fun photoDAO(): PhotoDAO

    abstract fun tagDAO(): TagDAO
}

package com.example.flickrpoc.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flickrpoc.model.Photo
import com.example.flickrpoc.model.PhotoDetails

/**
 * Interface for database access for User related operations.
 */
@Dao
interface PhotoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(photos: List<Photo>)

    @Query("SELECT * FROM photoDetails WHERE id = :id")
    fun getPhotoDetails(id: String): LiveData<PhotoDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotoDetails(photoDetails: PhotoDetails)

    @Query("SELECT * FROM photo")
    fun getPhotos(): LiveData<List<Photo>>

    @Query("SELECT * FROM photo WHERE tag = :tag")
    fun getPhotos(tag: String): LiveData<List<Photo>>

    @Query("SELECT * FROM photo WHERE tag = :tag")
    fun findPhotos(tag: String): List<Photo>
}

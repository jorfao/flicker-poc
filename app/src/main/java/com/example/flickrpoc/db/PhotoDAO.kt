package com.example.flickrpoc.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flickrpoc.model.Photo

/**
 * Interface for database access for User related operations.
 */
@Dao
interface PhotoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(photos: List<Photo>)

    @Query("SELECT * FROM photo")
    fun getPhotos(): LiveData<List<Photo>>
}

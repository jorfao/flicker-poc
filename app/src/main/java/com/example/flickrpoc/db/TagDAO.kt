package com.example.flickrpoc.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flickrpoc.model.Tag

/**
 * Interface for database access for User related operations.
 */
@Dao
interface TagDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTags(tags: List<Tag>)

    @Query("SELECT * FROM tag")
    fun getTags(): LiveData<List<Tag>>
}

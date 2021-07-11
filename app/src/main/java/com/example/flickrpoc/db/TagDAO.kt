package com.example.flickrpoc.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flickrpoc.model.Tag
import com.example.flickrpoc.model.TagPage

/**
 * Interface for database access for User related operations.
 */
@Dao
interface TagDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTags(tags: List<Tag>)

    @Query("SELECT * FROM tag")
    fun getTags(): LiveData<List<Tag>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(tagPage: TagPage)

    @Query("SELECT * FROM TagPage WHERE `tag` = :tag")
    fun getTagPages(tag: String): LiveData<TagPage?>

    @Query("SELECT * FROM TagPage WHERE `tag` = :tag")
    abstract fun findTagPage(tag: String): TagPage?
}

package com.example.flickrpoc.di

import android.app.Application
import androidx.room.Room
import com.example.flickrpoc.db.FlickrDB
import com.example.flickrpoc.db.Values.DB_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {
    @Provides
    @Singleton
    fun providesDB(app: Application) = Room
        .databaseBuilder(app, FlickrDB::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providesPhotoDAO(db: FlickrDB) = db.photoDAO()

    @Singleton
    @Provides
    fun providesTagDAO(db: FlickrDB) = db.tagDAO()
}

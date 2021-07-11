package com.example.flickrpoc.di

import com.example.flickrpoc.network.FlickrAPI
import com.example.flickrpoc.network.NetworkValues.BASE_URL
import com.example.flickrpoc.network.NetworkValues.TIME_OUT_SECONDS
import com.example.flickrpoc.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Reusable
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }.setLevel(HttpLoggingInterceptor.Level.BASIC)).build()
    }

    @Reusable
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    @Reusable
    @Provides
    fun providesFlickrApi(retrofit: Retrofit): FlickrAPI = retrofit.create(FlickrAPI::class.java)
}

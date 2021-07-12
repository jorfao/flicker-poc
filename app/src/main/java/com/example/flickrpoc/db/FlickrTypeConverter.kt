package com.example.flickrpoc.db

import androidx.room.TypeConverter

object FlickrTypeConverter {
    @TypeConverter
    @JvmStatic
    fun stringToStringList(data: String?): List<String>? {
        return data?.let {
            it.split(",")
        }
    }

    @TypeConverter
    @JvmStatic
    fun stringListToString(strings: List<String>?): String? {
        return strings?.joinToString(",")
    }

    @TypeConverter
    @JvmStatic
    fun stringToStringPairList(data: String?): List<Pair<String, String>>? = data?.split(",")?.map { Pair(it.substringBefore('|'), it.substringAfter('|')) }

    @TypeConverter
    @JvmStatic
    fun stringPairListToString(pairs: List<Pair<String, String>>?) = pairs?.joinToString(",") { it.first + "|" + it.second }
}

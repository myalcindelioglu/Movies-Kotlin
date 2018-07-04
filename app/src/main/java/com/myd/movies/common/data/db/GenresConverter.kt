package com.myd.movies.common.data.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.myd.movies.mvp.model.local.Genre

/**
 * Created by MYD on 6/3/18.
 */

class GenresConverter {

    @TypeConverter
    fun toGenres(genreString : String) : List<Genre> {
        val type = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson<List<Genre>>(genreString, type)
    }

    @TypeConverter
    fun fromGenresToString(genres : List<Genre>) : String {
        return Gson().toJson(genres)
    }
}
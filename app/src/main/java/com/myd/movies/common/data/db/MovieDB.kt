package com.myd.movies.common.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.myd.movies.mvp.model.local.Movie
import com.myd.movies.mvp.model.local.MovieDetail

/**
 * Created by MYD on 6/3/18.
 */
@Database(entities = [Movie::class, MovieDetail::class],
        version = MovieDB.VERSION,
        exportSchema = false)
@TypeConverters(GenresConverter::class)
abstract class MovieDB : RoomDatabase() {

    abstract fun getMovieDao() : MovieDao

    companion object {
        const val VERSION = 1
    }
}
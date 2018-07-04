package com.myd.movies.common.data.db

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.myd.movies.mvp.model.local.Movie
import com.myd.movies.mvp.model.local.MovieDetail

/**
 * Created by MYD on 6/3/18.
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies : List<Movie>)

    @Query("SELECT * FROM movies ORDER BY releaseDate DESC")
    fun queryMovies() : DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movies WHERE releaseDate <= :date ORDER BY releaseDate DESC")
    fun filterMoviesByReleaseDate(date: String) : DataSource.Factory<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(movieDetail: MovieDetail)

    @Query("SELECT * FROM movie_details WHERE id = :id")
    fun queryDetail(id : Int) : MovieDetail
}
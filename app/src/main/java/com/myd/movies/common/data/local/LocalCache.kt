package com.myd.movies.common.data.local

import android.arch.paging.DataSource
import com.myd.movies.mvp.model.local.Movie
import com.myd.movies.mvp.model.local.MovieDetail

/**
 * Created by MYD on 6/4/18.
 */
interface LocalCache {
    fun insertMovies(movies : List<Movie>?, insertFinished: () -> Unit)
    fun queryMovies() : DataSource.Factory<Int, Movie>
    fun filterMovies(date: String) : DataSource.Factory<Int, Movie>

    fun insertMovieDetail(movieDetail: MovieDetail, insertFinished: () -> Unit)
    fun queryMovieDetails(id : Int) : MovieDetail
}
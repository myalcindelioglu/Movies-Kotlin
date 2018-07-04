package com.myd.movies.common.data.local

import android.arch.paging.DataSource
import android.util.Log
import com.myd.movies.common.data.db.MovieDao
import com.myd.movies.mvp.model.local.Movie
import com.myd.movies.mvp.model.local.MovieDetail
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * Created by MYD on 6/4/18.
 */
class LocalMovieCache
@Inject
constructor(
        private val movieDao: MovieDao,
        private val ioExecutor: Executor
) : LocalCache {

    override fun queryMovies(): DataSource.Factory<Int, Movie> {
        Log.d(TAG, "querying movies")
        return movieDao.queryMovies()
    }

    override fun filterMovies(date: String): DataSource.Factory<Int, Movie> {
        Log.d(TAG, "filter movies date: ${date}")
        return movieDao.filterMoviesByReleaseDate(date)
    }

    override fun insertMovieDetail(movieDetail: MovieDetail, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Log.d(TAG, "inserting movie detail $movieDetail")
            movieDao.insertDetail(movieDetail)
            insertFinished()
        }
    }

    override fun queryMovieDetails(id: Int): MovieDetail {
        val result = movieDao.queryDetail(id)
        Log.d(TAG, "querying with id: $id getting result: $result")
        return result
    }


    override fun insertMovies(movies: List<Movie>?, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Log.d(TAG, "inserting ${movies?.size} movies")
            movieDao.insertMovies(movies!!)
            insertFinished()
        }
    }

    companion object {
        const val TAG = "LocalMovieCache"
    }

}
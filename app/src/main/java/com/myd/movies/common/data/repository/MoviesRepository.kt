package com.myd.movies.common.data.repository

import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import com.myd.movies.common.data.local.LocalCache
import com.myd.movies.common.data.remote.MoviesDataSource
import com.myd.movies.mvp.model.MovieLiveResult
import com.myd.movies.mvp.model.local.Movie
import com.myd.movies.mvp.model.local.MovieDetail
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by MYD on 6/4/18.
 */
class MoviesRepository @Inject
constructor(private val localCache: LocalCache,
            private val moviesDataSource: MoviesDataSource) : Repository {

    override fun discoverMovies(filterDate: String): MovieLiveResult {
        val dataSourceFactory: DataSource.Factory<Int, Movie>
        if (filterDate.isEmpty()) {
            dataSourceFactory = localCache.queryMovies()
        } else {
            dataSourceFactory = localCache.filterMovies(filterDate)
        }

        val movieBoundaryCallback = MovieBoundaryCallback(filterDate, localCache, moviesDataSource)
        val data = LivePagedListBuilder(dataSourceFactory,
                DATABASE_PAGE_SIZE)
                .setBoundaryCallback(movieBoundaryCallback)
                .build()
        return MovieLiveResult(data, movieBoundaryCallback.networkErrors)
    }

    override fun getDetails(movieId: Int): Single<MovieDetail> {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        return Single.create {  }
    }

    companion object {
        const val DATABASE_PAGE_SIZE = 10
    }

}
package com.myd.movies.common.data.repository

import com.myd.movies.mvp.model.MovieLiveResult
import com.myd.movies.mvp.model.local.MovieDetail
import io.reactivex.Single

/**
 * Created by MYD on 6/4/18.
 */
interface Repository {
    fun discoverMovies(filterDate: String): MovieLiveResult
    fun getDetails(movieId: Int): Single<MovieDetail>
}
package com.myd.movies.common.data.remote

import com.myd.movies.mvp.model.remote.MoviesRemoteResponse
import io.reactivex.Maybe

/**
 * Created by MYD on 6/1/18.
 */
interface MoviesDataSource {
    fun discoverMovies(page: Int): Maybe<MoviesRemoteResponse>
    fun filterMovies(date: String, page: Int): Maybe<MoviesRemoteResponse>
}
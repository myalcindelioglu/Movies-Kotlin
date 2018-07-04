package com.myd.movies.common.data.remote

import com.myd.movies.mvp.model.local.MovieDetail
import io.reactivex.Single

/**
 * Created by MYD on 6/1/18.
 */
interface MovieDetailsDataSource {
    fun getDetails(movieId: Int): Single<MovieDetail>
}
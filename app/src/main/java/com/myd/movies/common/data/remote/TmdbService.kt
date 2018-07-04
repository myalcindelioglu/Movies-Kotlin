package com.myd.movies.common.data.remote

import com.myd.movies.mvp.model.local.MovieDetail
import com.myd.movies.mvp.model.remote.MoviesRemoteResponse
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by MYD on 5/31/18.
 */
interface TmdbService {
    @GET("discover/movie?sort_by=release_date.desc")
    fun movieDiscover(@Query("page") page: Int): Maybe<MoviesRemoteResponse>

    @GET("discover/movie?sort_by=release_date.desc")
    fun movieDiscoverFilterReleaseDateDesc(
            @Query("release_date.lte") date: String, @Query("page") page: Int): Maybe<MoviesRemoteResponse>

    @GET("movie/{movieId}")
    fun getMovieDetails(@Path("movieId") movieId: Int): Single<MovieDetail>

}
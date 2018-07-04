package com.myd.movies.common.data.remote

import com.myd.movies.mvp.model.local.MovieDetail
import com.myd.movies.mvp.model.remote.MoviesRemoteResponse
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by MYD on 6/1/18.
 */
class MoviesRemoteDataSource @Inject constructor(private val tmdbService: TmdbService) : MoviesDataSource, MovieDetailsDataSource {

    override fun discoverMovies(page: Int): Maybe<MoviesRemoteResponse> {
        return tmdbService.movieDiscover(page)
    }

    override fun filterMovies(date: String, page: Int): Maybe<MoviesRemoteResponse> {
        return tmdbService.movieDiscoverFilterReleaseDateDesc(
                date, page)
    }

    override fun getDetails(movieId: Int): Single<MovieDetail> {
        return tmdbService.getMovieDetails(movieId)
    }
}
package com.myd.movies.mvp.model.remote

import android.support.annotation.VisibleForTesting
import com.myd.movies.common.data.remote.MoviesDataSource
import com.myd.movies.mvp.model.local.Movie
import io.reactivex.Maybe
import java.util.*
import javax.inject.Inject

/**
 * Created by MYD on 6/1/18.
 */
class FakeMovieDataSource @Inject
constructor() : MoviesDataSource {

    override fun discoverMovies(page: Int): Maybe<MoviesRemoteResponse> {
        return Maybe.just(SERVICE_DATA)
    }

    override fun filterMovies(date: String, page: Int): Maybe<MoviesRemoteResponse> {

        val movies = ArrayList<Movie>()
        for (movie in SERVICE_DATA.results!!) {
            movies.add(Movie(movie.id, movie.title, movie.posterPath, date))
        }
        return Maybe.just(
                MoviesRemoteResponse(
                        SERVICE_DATA.page,
                        SERVICE_DATA.total_results,
                        SERVICE_DATA.total_pages,
                        movies))
    }

    @VisibleForTesting
    fun setServiceData(remoteResponse: MoviesRemoteResponse) {
        SERVICE_DATA.page = remoteResponse.page
        SERVICE_DATA.total_results = remoteResponse.total_results
        SERVICE_DATA.total_pages = remoteResponse.total_pages
        SERVICE_DATA.results = remoteResponse.results
    }

    companion object {

        val SERVICE_DATA = MoviesRemoteResponse(1, 0, 1, ArrayList())
    }
}

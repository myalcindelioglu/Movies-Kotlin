package com.myd.movies.mvp.model.remote

import android.support.annotation.VisibleForTesting
import com.myd.movies.common.data.remote.MovieDetailsDataSource
import com.myd.movies.mvp.model.local.MovieDetail
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by MYD on 6/1/18.
 */
class FakeDetailsDataSource @Inject
constructor() : MovieDetailsDataSource {

    override fun getDetails(movieId: Int): Single<MovieDetail> {
        return Single.just<MovieDetail>(SERVICE_DATA)
    }

    @VisibleForTesting
    fun setServiceData(details: MovieDetail) {
        SERVICE_DATA.id = details.id
        SERVICE_DATA.title = details.title
        SERVICE_DATA.posterPath = details.posterPath
        SERVICE_DATA.backdropPath = details.backdropPath
        SERVICE_DATA.releaseDate = details.releaseDate
        SERVICE_DATA.originalLanguage = details.originalLanguage
        SERVICE_DATA.voteAverage = details.voteAverage
        SERVICE_DATA.voteCount = details.voteCount
        SERVICE_DATA.overview = details.overview
        SERVICE_DATA.genres = details.genres
    }

    companion object {
        private val SERVICE_DATA = MovieDetail()
    }

}
package com.myd.movies.util

import com.myd.movies.mvp.model.local.Genre
import com.myd.movies.mvp.model.local.Movie
import com.myd.movies.mvp.model.local.MovieDetail
import com.myd.movies.mvp.model.remote.MoviesRemoteResponse
import java.util.*

/**
 * Created by MYD on 6/1/18.
 */
object TestUtil {
    fun createMovieDetails(movie: Movie?): MovieDetail {
        val genre = Genre(1, "drama")
        return MovieDetail(movie?.id,
                movie?.title,
                movie?.posterPath,
                "/ducVIDxVa6BjlMl976XLmogBRkI.jpg",
                movie?.releaseDate,
                "en",
                8.9,
                1000,
                "overview text",
                listOf(genre))

    }

    fun createMovie(id: Int, releaseDate: String): Movie {

        return Movie(id, "Batman",
                "/kns75wPtBCISvnWddHFEo0DMuU0.jpg",
                releaseDate)
    }

    fun createMovies(): List<Movie> {
        val movies = ArrayList<Movie>()

        val date1 = "2018-2-27"
        val date2 = "2018-2-26"

        for (i in 1..10) {
            movies.add(createMovie(i, date1))
        }

        for (i in 11..20) {
            movies.add(createMovie(i, date2))
        }

        return movies
    }

    fun createMoviesRemoteResponse(page: Int, totalResults: Int, totalPages: Int, movies: List<Movie>): MoviesRemoteResponse {
        return MoviesRemoteResponse(page, totalResults,
                totalPages, movies)
    }

    fun createMoviesRemoteResponse(movie: Movie): MoviesRemoteResponse {
        return createMoviesRemoteResponse(1, 1,
                1, listOf(movie))
    }
}
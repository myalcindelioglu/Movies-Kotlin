package com.myd.movies.mvp.model.remote

import com.myd.movies.common.data.remote.MoviesRemoteDataSource
import com.myd.movies.common.data.remote.TmdbService
import com.myd.movies.mvp.model.local.MovieDetail
import com.myd.movies.util.TestUtil
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Created by MYD on 6/1/18.
 */
class MoviesRemoteDataSourceTest {

    private var remoteDataSource: MoviesRemoteDataSource? = null

    @Mock
    private val service: TmdbService? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = MoviesRemoteDataSource(service!!)
    }

    @Test
    @Throws(Exception::class)
    fun testDiscoverMovies() {
        val date = "2018-2-27"
        val movie = TestUtil.createMovie(1, date)
        val response = TestUtil.createMoviesRemoteResponse(movie)

        `when`(service!!.movieDiscover(1)).thenReturn(Maybe.just<MoviesRemoteResponse>(response))
        val testObserver = remoteDataSource!!.discoverMovies(1).test()
        testObserver.assertNoErrors()
        testObserver.assertValue(response)
    }

    @Test
    @Throws(Exception::class)
    fun testFilterMovies() {
        val date = "2018-2-27"
        val movie = TestUtil.createMovie(1, date)
        val response = TestUtil.createMoviesRemoteResponse(movie)

        `when`(service!!.movieDiscoverFilterReleaseDateDesc(date, 1)).thenReturn(Maybe.just<MoviesRemoteResponse>(response))
        val testObserver = remoteDataSource!!.filterMovies(date, 1).test()
        testObserver.assertNoErrors()
        testObserver.assertValue(response)
    }

    @Test
    @Throws(Exception::class)
    fun testGetDetails() {

        val movie = TestUtil.createMovie(1, "2018-04-12")
        val movieDetails = TestUtil.createMovieDetails(movie)
        Mockito.`when`(service!!.getMovieDetails(movie.id)).thenReturn(Single.just<MovieDetail>(movieDetails))
        val test = remoteDataSource!!.getDetails(movie.id).test()
        test.assertNoErrors()
        test.assertValue(movieDetails)
    }
}
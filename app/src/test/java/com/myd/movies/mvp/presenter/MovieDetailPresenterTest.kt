package com.myd.movies.mvp.presenter

import com.myd.movies.common.data.remote.MovieDetailsDataSource
import com.myd.movies.common.data.repository.Repository
import com.myd.movies.mvp.MovieDetailContract
import com.myd.movies.mvp.model.local.MovieDetail
import com.myd.movies.util.TestUtil
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by MYD on 6/1/18.
 */
class MovieDetailPresenterTest {
    private var presenter: MovieDetailPresenter? = null

    @Mock
    private val dataSource: MovieDetailsDataSource? = null

    @Mock
    private val repository: Repository? = null

    @Mock
    private val view: MovieDetailContract.View? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        presenter = MovieDetailPresenter(repository!!)
        presenter!!.subscribe(view!!)
    }

    @Test
    @Throws(Exception::class)
    fun testGetDetails() {
        val movieId = 1
        val movieDetails = TestUtil.createMovieDetails(TestUtil.createMovie(movieId, "2017-12-1"))
        Mockito.`when`(dataSource!!.getDetails(movieId)).thenReturn(Single.just<MovieDetail>(movieDetails))
        presenter!!.getDetails(movieId)
        Mockito.verify(view, Mockito.timeout(400).times(1))?.showProgress()
        Mockito.verify(view, Mockito.timeout(400).times(1))?.loadViews(movieDetails)
    }

}
package com.myd.movies.mvp.presenter

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.*
import android.arch.paging.PagedList
import com.myd.movies.common.data.remote.MoviesRemoteDataSource
import com.myd.movies.common.data.repository.Repository
import com.myd.movies.mvp.model.local.Movie
import com.myd.movies.mvp.model.remote.MoviesRemoteResponse
import com.myd.movies.mvp.view.MovieListFragment
import com.myd.movies.util.TestUtil
import io.reactivex.Maybe
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by MYD on 6/1/18.
 */
class MovieListPresenterTest {

    private var presenter: MovieListPresenter? = null

    @Mock
    private val dataSource: MoviesRemoteDataSource? = null

    @Mock
    private val repository: Repository? = null

    @Mock
    private val view: MovieListFragment? = null

    private val movieLiveData: MutableLiveData<String> = MutableLiveData()

    inline fun <reified T> lambdaMock(): T = mock(T::class.java)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {Schedulers.trampoline()}
        presenter = MovieListPresenter(repository!!, movieLiveData)
        val dataObserver = lambdaMock<Observer<PagedList<Movie>>>()
        val errorObserver = lambdaMock<Observer<String>>()
        val lifecycle = mock(Lifecycle::class.java)
        val lifecycleOwner = mock(LifecycleOwner::class.java)
        `when`(lifecycleOwner.lifecycle).thenReturn(lifecycle)
        val lifecycleRegistry  = LifecycleRegistry(lifecycleOwner)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        presenter!!.subscribe(view!!)
        presenter!!.observeResult(lifecycleOwner, dataObserver, errorObserver)
    }

    @Test
    @Throws(Exception::class)
    fun testDiscoverMovies() {
        val date = "2018-2-27"
        val movie = TestUtil.createMovie(1, date)
        val response = TestUtil.createMoviesRemoteResponse(movie)

        `when`(dataSource!!.discoverMovies(1)).thenReturn(Maybe.just<MoviesRemoteResponse>(response))
        presenter!!.discoverMovies()
        verify(view, times(1))?.showProgress()
        assertEquals("", movieLiveData.value)

    }

    @Test
    @Throws(Exception::class)
    fun testFilterMovies() {
        val date = "2018-2-27"
        val movie = TestUtil.createMovie(1, date)
        val response = TestUtil.createMoviesRemoteResponse(movie)

        `when`(dataSource!!.filterMovies(date, 1)).thenReturn(Maybe.just<MoviesRemoteResponse>(response))
        presenter!!.filterMovies(date)
        verify(view, times(1))?.showProgress()
//        verify(view, timeout(400).times(1))?.showData(listOf(movie), false)
    }

}
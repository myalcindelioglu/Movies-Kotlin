package com.myd.movies.mvp.presenter

import com.myd.movies.mvp.MainContract
import com.myd.movies.util.DateUtil
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by MYD on 6/1/18.
 */
class MainPresenterTest {

    private var presenter: MainPresenter? = null

    @Mock
    private val view: MainContract.View? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter()
        presenter!!.subscribe(view!!)
    }

    @Test
    @Throws(Exception::class)
    fun testHandleOnMovieClick() {
        presenter!!.handleOnMovieClick(1)
        verify(view, times(1))?.showDetail(1)
    }

    @Test
    @Throws(Exception::class)
    fun testFilterMovies() {
        val date = DateUtil.intToString(2017, 4, 13)
        presenter!!.filterMovies(date)
        verify(view, times(1))?.showFilteredMovies(date)
    }

    @Test
    @Throws(Exception::class)
    fun testHandleFilterClick() {
        presenter!!.handleFilterClick()
        verify(view, times(1))?.showDatePicker()
    }

    @Test
    @Throws(Exception::class)
    fun testHandleBackPress() {
        presenter!!.handleBackPress()
        verify(view, times(1))?.showMovieList()
    }

    @Test
    @Throws(Exception::class)
    fun testSubscribe() {
        verify(view, times(1))?.subscribeMovieOnClick()
    }
}
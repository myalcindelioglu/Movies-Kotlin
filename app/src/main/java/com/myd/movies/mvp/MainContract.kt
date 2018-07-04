package com.myd.movies.mvp

import com.myd.movies.common.base.BasePresenter

/**
 * Created by MYD on 6/1/18.
 */
interface MainContract {
    interface View {
        fun subscribeMovieOnClick()
        fun showDetail(movieId: Int)
        fun showFilteredMovies(date: String)
        fun showDatePicker()
        fun showMovieList()
    }

    interface Presenter : BasePresenter<View> {
        fun filterMovies(date: String)
        fun handleOnMovieClick(movieId: Int)
        fun handleFilterClick()
        fun handleBackPress()
    }
}
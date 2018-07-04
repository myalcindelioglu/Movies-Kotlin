package com.myd.movies.mvp

import android.arch.lifecycle.LifecycleOwner
import android.arch.paging.PagedList
import com.myd.movies.common.base.BasePresenter
import com.myd.movies.mvp.model.local.Movie

/**
 * Created by MYD on 6/1/18.
 */
interface MovieListContract {
    interface View : LifecycleOwner {
        fun showProgress()
        fun showData(movies: PagedList<Movie>?)
        fun showError()
    }

    interface Presenter : BasePresenter<View> {
        fun discoverMovies()
        fun filterMovies(filterDate: String)
    }
}
package com.myd.movies.mvp

import com.myd.movies.common.base.BasePresenter
import com.myd.movies.mvp.model.local.MovieDetail

/**
 * Created by MYD on 6/1/18.
 */
interface MovieDetailContract {
    interface View {
        fun showProgress()
        fun loadViews(movieDetail: MovieDetail)
        fun showError()
    }

    interface Presenter : BasePresenter<View> {
        fun getDetails(movieId: Int)
    }
}
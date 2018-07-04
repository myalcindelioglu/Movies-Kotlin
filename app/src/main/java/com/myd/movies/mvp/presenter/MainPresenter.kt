package com.myd.movies.mvp.presenter

import com.myd.movies.mvp.MainContract
import javax.inject.Inject

/**
 * Created by MYD on 6/1/18.
 */
class MainPresenter @Inject
constructor() : MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun filterMovies(date: String) {
        view!!.showFilteredMovies(date)
    }

    override fun handleOnMovieClick(movieId: Int) {
        view!!.showDetail(movieId)
    }

    override fun handleFilterClick() {
        view!!.showDatePicker()
    }

    override fun handleBackPress() {
        view!!.showMovieList()
    }

    override fun subscribe(view: MainContract.View) {
        this.view = view
        this.view!!.subscribeMovieOnClick()
    }

    override fun unSubscribe() {}
}
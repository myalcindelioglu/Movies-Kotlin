package com.myd.movies.mvp.presenter

import android.util.Log
import com.myd.movies.common.data.repository.Repository
import com.myd.movies.mvp.MovieDetailContract
import com.myd.movies.mvp.model.local.MovieDetail
import com.myd.movies.util.RxUtil
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by MYD on 6/1/18.
 */
class MovieDetailPresenter
@Inject
constructor(private val repository: Repository) : MovieDetailContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private var view: MovieDetailContract.View? = null

    override fun getDetails(movieId: Int) {
        view!!.showProgress()
        val movieDetailsSingle = repository.getDetails(movieId).compose(RxUtil.applySingleSchedulers<MovieDetail>())

        subscriptions.add(movieDetailsSingle.subscribe(
                { movieDetail: MovieDetail ->
                    Log.d(TAG, "getDetails = $movieDetail")
                    view!!.loadViews(movieDetail)
                }, { e: Throwable ->
                    Log.e(TAG, "getDetails has an error for movieId= $movieId", e)
                    view!!.showError()
                }
        ))
    }

    override fun subscribe(view: MovieDetailContract.View) {
        this.view = view

    }

    override fun unSubscribe() {
        RxUtil.unSubscribe(subscriptions)
    }

    companion object {

        private val TAG = "MovieDetailPresenter"
    }
}
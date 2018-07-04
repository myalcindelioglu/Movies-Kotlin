package com.myd.movies.mvp.presenter

import android.arch.lifecycle.*
import android.arch.paging.PagedList
import com.myd.movies.common.data.repository.Repository
import com.myd.movies.mvp.MovieListContract
import com.myd.movies.mvp.model.MovieLiveResult
import com.myd.movies.mvp.model.local.Movie
import javax.inject.Inject

/**
 * Created by MYD on 6/1/18.
 */
class MovieListPresenter @Inject
constructor(private val repository: Repository,
            private val movieLiveData: MutableLiveData<String>) : MovieListContract.Presenter {

    private var view: MovieListContract.View? = null

    private var filterDate = ""

    private val movieLiveResult: LiveData<MovieLiveResult> = Transformations.map(movieLiveData, {
        repository.discoverMovies(filterDate)
    })

    private val moviePagedLiveData: LiveData<PagedList<Movie>> = Transformations.switchMap(movieLiveResult, {
        it  -> it.data
    })

    private val networkErrors: LiveData<String> = Transformations.switchMap(movieLiveResult,
            { it -> it.networkErrors })

    override fun discoverMovies() {

        view!!.showProgress()
        movieLiveData.postValue(filterDate)

    }

    fun observeResult(lifecycleOwner: LifecycleOwner,
                      dataObserver: Observer<PagedList<Movie>>,
                      errorObserver: Observer<String>) {
        moviePagedLiveData.observe( lifecycleOwner, dataObserver)

        networkErrors.observe(lifecycleOwner, errorObserver)
    }

    override fun filterMovies(filterDate: String) {

        this.filterDate = filterDate
        view!!.showData(null)
        discoverMovies()
    }

    override fun subscribe(view: MovieListContract.View) {
        this.view = view
    }

    override fun unSubscribe() {

    }

    companion object {
        private const val TAG = "MovieListPresenter"
    }
}
package com.myd.movies.common.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.myd.movies.common.data.local.LocalCache
import com.myd.movies.common.data.remote.MoviesDataSource
import com.myd.movies.mvp.model.local.Movie
import com.myd.movies.mvp.model.remote.MoviesRemoteResponse
import com.myd.movies.util.RxUtil

/**
 * Created by MYD on 6/4/18.
 */
class MovieBoundaryCallback
constructor(private val filterDate: String,
            private val localCache: LocalCache,
            private val moviesDataSource: MoviesDataSource) : PagedList.BoundaryCallback<Movie>() {

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false
    private var totalPages = 1
    private var lastRequestedPage = 1

    // LiveData of network errors.
    val _networkErrors = MutableLiveData<String>()

    val networkErrors: LiveData<String>
        get() = _networkErrors

    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        requestAndSaveData()
    }


    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        if (totalPages >= lastRequestedPage) {
            val responseMaybe = if (filterDate.isEmpty()) {
                moviesDataSource.discoverMovies(lastRequestedPage).compose(RxUtil.applyMaybeSchedulers<MoviesRemoteResponse>())
            } else {
                moviesDataSource.filterMovies(filterDate, lastRequestedPage).compose(RxUtil.applyMaybeSchedulers<MoviesRemoteResponse>())
            }

            responseMaybe.subscribe({ response ->
                lastRequestedPage++
                totalPages = response.total_pages
                localCache.insertMovies(response.results, {
                    isRequestInProgress = !isRequestInProgress
                })
            },
                    { error ->
                        _networkErrors.postValue(error.message)
                        isRequestInProgress = !isRequestInProgress
                    })
        }
    }


}
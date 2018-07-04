package com.myd.movies.mvp.view

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

/**
 * Created by MYD on 6/1/18.
 */
abstract class InfiniteScrollListener private constructor(private val layoutManager: RecyclerView.LayoutManager,
                                                          private val threshold: Int,
                                                          private var lastPage: Int) : RecyclerView.OnScrollListener() {
    private var currentPage: Int = 0
    private var lastTotalItemCount = 0
    private var initialPage = 1

    private var loading = true

    internal constructor(layoutManager: RecyclerView.LayoutManager) : this(layoutManager, 5, 1)

    init {
        this.currentPage = lastPage
        this.initialPage = lastPage
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = layoutManager.itemCount

        if (layoutManager is StaggeredGridLayoutManager) {
            val lastVisibleItemPositions = layoutManager.findLastVisibleItemPositions(null)
            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
        } else if (layoutManager is GridLayoutManager) {
            lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        } else if (layoutManager is LinearLayoutManager) {
            lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        }

        if (totalItemCount < lastTotalItemCount) {
            this.currentPage = this.initialPage
            this.lastTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        if (loading && totalItemCount > lastTotalItemCount) {
            loading = false
            lastTotalItemCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + threshold > totalItemCount) {
            lastPage = currentPage++
            onLoadMore(currentPage)
            loading = true
        }

    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    internal fun reset() {
        lastTotalItemCount = 0
        lastPage = initialPage
        currentPage = initialPage
        loading = true
    }

    internal fun onLoadError() {
        currentPage = lastPage
        loading = false
    }

    abstract fun onLoadMore(nextPage: Int)
}
package com.myd.movies.common.base

/**
 * Created by MYD on 5/31/18.
 */
interface BasePresenter<T> {
    fun subscribe(view: T)
    fun unSubscribe()
}
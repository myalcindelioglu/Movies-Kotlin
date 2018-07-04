package com.myd.movies.mvp.model.local

/**
 * Created by MYD on 6/3/18.
 */
class Genre(var id: Int, name: String) {
    var name: String? = name

    override fun toString(): String {
        return name ?: ""
    }
}
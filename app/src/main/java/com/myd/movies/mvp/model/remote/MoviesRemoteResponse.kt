package com.myd.movies.mvp.model.remote

import com.myd.movies.mvp.model.local.Movie

/**
 * Created by MYD on 6/1/18.
 */
class MoviesRemoteResponse {
    var page: Int = 0
    var total_results: Int = 0
    var total_pages: Int = 0
    var results: List<Movie>? = null

    constructor() {}

    constructor(page: Int, total_results: Int, total_pages: Int, results: List<Movie>) {
        this.page = page
        this.total_results = total_results
        this.total_pages = total_pages
        this.results = results
    }

    override fun toString(): String {
        return "MoviesRemoteResponse{" +
                "page=" + page +
                ", total_results=" + total_results +
                ", total_pages=" + total_pages +
                ", results=" + results +
                '}'.toString()
    }
}
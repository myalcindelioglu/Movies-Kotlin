package com.myd.movies.mvp.view

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myd.movies.R
import com.myd.movies.mvp.MovieListContract
import com.myd.movies.mvp.model.local.Movie
import com.myd.movies.mvp.presenter.MovieListPresenter
import dagger.android.support.DaggerFragment
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by MYD on 6/1/18.
 */


class MovieListFragment : DaggerFragment(), MovieListContract.View {

    var presenter: MovieListPresenter? = null
        @Inject set

    var onClicks: PublishSubject<Int>? = null
        @Inject set

    var moviesAdapter: MoviesAdapter? = null
        @Inject set

    private var loadProgress: View? = null
    private var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter!!.subscribe(this)
        presenter!!.observeResult(this,
                dataObserver = Observer { this.showData(it) },
                errorObserver = Observer { this.showError() })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        loadProgress = view.findViewById(R.id.fragment_movie_list_load_pb)
        recyclerView = view.findViewById(R.id.fragment_movie_list_rcv)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = linearLayoutManager

        recyclerView!!.adapter = moviesAdapter

        presenter!!.discoverMovies()

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.unSubscribe()
    }

    override fun showProgress() {
        loadProgress!!.visibility = View.VISIBLE
    }

    override fun showData(movies: PagedList<Movie>?) {
        hideProgress()
        if (movies == null || movies.isEmpty()) {
            moviesAdapter!!.submitList(null)
        } else {
            moviesAdapter!!.submitList(movies)
        }

    }

    override fun showError() {
        hideProgress()
        Snackbar.make(recyclerView!!, R.string.network_error_text, BaseTransientBottomBar.LENGTH_LONG).show()
    }

    private fun hideProgress() {
        loadProgress!!.visibility = View.GONE
    }

    fun filterMovies(date: String) {
        presenter!!.filterMovies(date)
    }
}
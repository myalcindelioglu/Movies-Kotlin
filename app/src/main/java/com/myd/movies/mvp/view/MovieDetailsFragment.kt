package com.myd.movies.mvp.view

import android.os.Bundle
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.myd.movies.BuildConfig
import com.myd.movies.R
import com.myd.movies.mvp.MovieDetailContract
import com.myd.movies.mvp.model.local.MovieDetail
import com.myd.movies.mvp.presenter.MovieDetailPresenter
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject

/**
 * Created by MYD on 6/1/18.
 */


class MovieDetailsFragment : DaggerFragment(), MovieDetailContract.View {

    var presenter: MovieDetailPresenter? = null
        @Inject set

    private var posterImage: ImageView? = null
    private var backdropImage: ImageView? = null
    private var titleText: TextView? = null
    private var languageText: TextView? = null
    private var dateText: TextView? = null
    private var genresText: TextView? = null
    private var rateText: TextView? = null
    private var rateCountText: TextView? = null
    private var overviewText: TextView? = null
    private var progressBar: ProgressBar? = null

    private var rootView: View? = null

    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            movieId = args.getInt(MOVIE_ID_KEY, -1)
        }
        presenter!!.subscribe(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        posterImage = view.findViewById(R.id.fragment_movie_details_poster_iv)
        backdropImage = view.findViewById(R.id.fragment_movie_details_backdrop_iv)
        titleText = view.findViewById(R.id.fragment_movie_details_title_txv)
        languageText = view.findViewById(R.id.fragment_movie_details_lang_txv)
        dateText = view.findViewById(R.id.fragment_movie_details_date_txv)
        genresText = view.findViewById(R.id.fragment_movie_details_genres_txv)
        rateText = view.findViewById(R.id.fragment_movie_details_rate_txv)
        rateCountText = view.findViewById(R.id.fragment_movie_details_count_txv)
        overviewText = view.findViewById(R.id.fragment_movie_details_overview_txv)
        progressBar = view.findViewById(R.id.fragment_movie_details_load_pb)
        rootView = view.findViewById(R.id.fragment_movie_details_cl)

        presenter!!.getDetails(movieId)

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.unSubscribe()
    }

    override fun showProgress() {
        progressBar!!.visibility = View.VISIBLE
    }

    override fun showError() {
        progressBar!!.visibility = View.GONE
        Snackbar.make(rootView!!, R.string.network_error_text, BaseTransientBottomBar.LENGTH_LONG).show()
    }

    override fun loadViews(movieDetail: MovieDetail) {
        progressBar!!.visibility = View.GONE
        val posterPath = movieDetail.posterPath
        if (posterPath != null && !posterPath.isEmpty()) {
            Picasso.with(context)
                    .load(BuildConfig.TMDB_SECURE_IMAGE_URL + "w780" + posterPath)
                    .into(posterImage
                    )

        }

        val backdropPath = movieDetail.posterPath
        if (backdropPath != null && !backdropPath.isEmpty()) {
            Picasso.with(context)
                    .load(BuildConfig.TMDB_SECURE_IMAGE_URL + "w780" + backdropPath)
                    .into(backdropImage
                    )

        }

        titleText!!.text = movieDetail.title
        languageText!!.text = Locale(movieDetail.originalLanguage).displayName
        dateText!!.text = movieDetail.releaseDate
        genresText!!.text = TextUtils.join(",", movieDetail.genres)
        rateText!!.text = getString(R.string.movie_detail_rate, movieDetail.voteAverage)
        rateCountText!!.text = movieDetail.voteCount.toString()
        overviewText!!.text = movieDetail.overview
    }

    companion object {

        const val MOVIE_ID_KEY = "MOVIE_ID"

        fun newInstance(movieId: Int): MovieDetailsFragment {
            val movieDetailsFragment = MovieDetailsFragment()
            val args = Bundle()
            args.putInt(MOVIE_ID_KEY, movieId)
            movieDetailsFragment.arguments = args
            return movieDetailsFragment
        }
    }
}
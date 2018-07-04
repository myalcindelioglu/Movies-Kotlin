package com.myd.movies.mvp.view

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.myd.movies.BuildConfig
import com.myd.movies.R
import com.myd.movies.mvp.model.local.Movie
import com.squareup.picasso.Picasso
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by MYD on 6/1/18.
 */
class MoviesAdapter @Inject
constructor(private val movieIdOnClickPublisher: PublishSubject<Int>) :
        PagedListAdapter<Movie, MoviesAdapter.MoviesViewHolder>(MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_movies_list_item, parent, false)
        val poster = view.findViewById<ImageView>(R.id.fragment_movie_list_item_movies_poster_iv)
        val title = view.findViewById<TextView>(R.id.fragment_movie_list_item_movies_title_txv)
        val releaseDate = view.findViewById<TextView>(R.id.fragment_movie_list_item_movies_release_date_txv)
        return MoviesViewHolder(view, poster, title, releaseDate)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)
        val posterPath = item?.posterPath
        Picasso.with(holder.poster.context)
                .load(BuildConfig.TMDB_SECURE_IMAGE_URL + "w500" + posterPath)
                .into(holder.poster)
        holder.title.text = item?.title
        holder.releaseDate.text = item?.releaseDate

        val movieId = item?.id

        movieId ?: return
        holder.itemView.setOnClickListener { movieIdOnClickPublisher.onNext(movieId) }

    }

    inner class MoviesViewHolder(itemView: View,
                                          internal val poster: ImageView,
                                          internal val title: TextView,
                                          internal val releaseDate: TextView) : RecyclerView.ViewHolder(itemView)

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                    oldItem == newItem
        }
    }
}
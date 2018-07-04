package com.myd.movies.mvp.model.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by MYD on 5/31/18.
 */
@Entity(tableName = "movie_details")
class MovieDetail {
    @PrimaryKey
    @field:SerializedName("id") var id: Int? = 0
    @field:SerializedName("title") var title: String? = null
    @field:SerializedName("poster_path") var posterPath: String? = null
    @field:SerializedName("backdrop_path") var backdropPath: String? = null
    @field:SerializedName("release_date") var releaseDate: String? = null
    @field:SerializedName("original_language") var originalLanguage: String? = null
    @field:SerializedName("vote_average") var voteAverage: Double = 0.toDouble()
    @field:SerializedName("vote_count") var voteCount: Int = 0
    @field:SerializedName("overview") var overview: String? = null
    @field:SerializedName("genres") var genres: List<Genre>? = null


    constructor() {}

    constructor(id: Int?,
                title: String?,
                poster_path: String?,
                backdrop_path: String?,
                release_date: String?,
                original_language: String?,
                vote_average: Double,
                vote_count: Int,
                overview: String?,
                genres: List<Genre>?) {
        this.id = id
        this.title = title
        this.posterPath = poster_path
        this.backdropPath = backdrop_path
        this.releaseDate = release_date
        this.originalLanguage = original_language
        this.voteAverage = vote_average
        this.voteCount = vote_count
        this.overview = overview
        this.genres = genres
    }

    override fun toString(): String {
        return "MovieDetail{" +
                "id=" + id +
                ", title='" + title + '\''.toString() +
                ", backdrop_path='" + backdropPath + '\''.toString() +
                ", poster_path='" + posterPath + '\''.toString() +
                ", release_date='" + releaseDate + '\''.toString() +
                ", original_language='" + originalLanguage + '\''.toString() +
                ", vote_average=" + voteAverage +
                ", vote_count=" + voteCount +
                ", overview='" + overview + '\''.toString() +
                ", genres=" + genres +
                '}'.toString()
    }
}
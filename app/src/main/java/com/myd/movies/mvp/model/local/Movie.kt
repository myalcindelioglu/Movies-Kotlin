package com.myd.movies.mvp.model.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by MYD on 5/31/18.
 */
@Entity(tableName = "movies")
class Movie(@PrimaryKey
            @field:SerializedName("id") var id: Int,
            @field:SerializedName("title") var title: String?,
            @field:SerializedName("poster_path") var posterPath: String?,
            @field:SerializedName("release_date") var releaseDate: String?) {

    override fun toString(): String {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\''.toString() +
                ", poster_path='" + posterPath + '\''.toString() +
                ", release_date='" + releaseDate + '\''.toString() +
                '}'.toString()
    }
}
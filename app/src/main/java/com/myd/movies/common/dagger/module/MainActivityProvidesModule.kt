package com.myd.movies.common.dagger.module

import com.myd.movies.common.dagger.annotations.ActivityScoped
import com.myd.movies.mvp.view.MoviesAdapter
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject

/**
 * Created by MYD on 6/1/18.
 */
@Module
class MainActivityProvidesModule {

    @ActivityScoped
    @Provides
    fun provideMoviesAdapter(movieIdOnClickPublisher: PublishSubject<Int>): MoviesAdapter {
        return MoviesAdapter(movieIdOnClickPublisher)
    }

    @ActivityScoped
    @Provides
    fun provideMovieIdOnClickPublisher(): PublishSubject<Int> {
        return PublishSubject.create()
    }
}
package com.myd.movies.common.dagger.module

import com.myd.movies.common.dagger.annotations.ActivityScoped
import com.myd.movies.common.dagger.annotations.FragmentScoped
import com.myd.movies.mvp.MainContract
import com.myd.movies.mvp.MovieDetailContract
import com.myd.movies.mvp.MovieListContract
import com.myd.movies.mvp.presenter.MainPresenter
import com.myd.movies.mvp.presenter.MovieDetailPresenter
import com.myd.movies.mvp.presenter.MovieListPresenter
import com.myd.movies.mvp.view.MovieDetailsFragment
import com.myd.movies.mvp.view.MovieListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by MYD on 5/31/18.
 */
@Module
internal abstract class MainActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun movieListFragment(): MovieListFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun movieDetailsFragment(): MovieDetailsFragment

    @ActivityScoped
    @Binds
    internal abstract fun mainPresenter(presenter: MainPresenter): MainContract.Presenter

    @FragmentScoped
    @Binds
    internal abstract fun movieListPresenter(presenter: MovieListPresenter): MovieListContract.Presenter

    @FragmentScoped
    @Binds
    internal abstract fun movieDetailPresenter(presenter: MovieDetailPresenter): MovieDetailContract.Presenter
}
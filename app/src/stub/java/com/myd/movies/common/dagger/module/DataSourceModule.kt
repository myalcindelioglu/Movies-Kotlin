package com.myd.movies.common.dagger.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.myd.movies.BuildConfig
import com.myd.movies.common.data.remote.MoviesDataSource
import com.myd.movies.common.data.remote.MoviesRemoteDataSource
import com.myd.movies.common.data.remote.TmdbService
import com.myd.movies.mvp.model.remote.FakeMovieDataSource
import com.myd.movies.util.TmdbApiInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by MYD on 6/1/18.
 */
@Module
class DataSourceModule {

    @Singleton
    @Provides
    internal fun provideMoviesDataSource(serverInterface: TmdbService): MoviesDataSource {
        return FakeMovieDataSource()
    }

    @Provides
    @Singleton
    internal fun provideServerInterface(gson: Gson, okHttpClient: OkHttpClient): TmdbService {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.TMDB_SECURE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create(TmdbService::class.java)
    }


    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(10, TimeUnit.SECONDS)

        // build okhttp client
        return builder
                .addInterceptor(TmdbApiInterceptor())
                .build()
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create()
    }

    @Provides
    @Singleton
    internal fun provideApiKeyRequestInterceptor(): TmdbApiInterceptor {
        return TmdbApiInterceptor()
    }

}
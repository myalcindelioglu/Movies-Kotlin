package com.myd.movies.mvp.view

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.PickerActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.DatePicker
import com.myd.movies.R
import com.myd.movies.mvp.model.remote.FakeDetailsDataSource
import com.myd.movies.mvp.model.remote.FakeMovieDataSource
import com.myd.movies.util.RecyclerViewMatcher
import com.myd.movies.util.TestUtil
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by MYD on 6/1/18.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val source = FakeMovieDataSource()
        val movies = TestUtil.createMovies()
        val moviesRemoteResponse = TestUtil.createMoviesRemoteResponse(1, movies.size, 1, movies)
        source.setServiceData(moviesRemoteResponse)
        activityTestRule.launchActivity(Intent())
    }

    @Test
    @Throws(Exception::class)
    fun testListFragment() {

        onView(withId(R.id.fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_movie_list_rcv)).check(matches(isDisplayed()))

        onView(withId(R.id.fragment_movie_list_rcv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        val movie3 = FakeMovieDataSource.SERVICE_DATA.results?.get(3)
        onView(RecyclerViewMatcher.withRecyclerView(R.id.fragment_movie_list_rcv).atPosition(3)).check(matches(hasDescendant(withText(movie3?.releaseDate!!))))
    }

    @Test
    @Throws(Exception::class)
    fun testDetailFragment() {

        val detailsDataSource = FakeDetailsDataSource()
        val movie3 = FakeMovieDataSource.SERVICE_DATA.results?.get(3)
        detailsDataSource.setServiceData(TestUtil.createMovieDetails(movie3))

        onView(withId(R.id.fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_movie_list_rcv)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_movie_list_rcv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(R.id.action_filter)).check(doesNotExist())
        onView(withId(R.id.fragment_movie_details_date_txv)).check(matches(isDisplayed())).check(matches(withText(movie3?.releaseDate)))

        onView(withContentDescription(R.string.abc_action_bar_up_description)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.fragment_movie_list_rcv)).check(matches(isDisplayed()))
    }

    @Test
    @Throws(Exception::class)
    fun testDatePicker() {
        onView(withId(R.id.action_filter)).check(matches(isDisplayed()))
        onView(withId(R.id.action_filter)).perform(click())
        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name))).perform(PickerActions.setDate(2018, 2, 27))
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.fragment_movie_list_rcv)).check(matches(isDisplayed()))

        onView(RecyclerViewMatcher.withRecyclerView(R.id.fragment_movie_list_rcv).atPosition(0)).check(matches(hasDescendant(withText("2018-2-27"))))

    }

}
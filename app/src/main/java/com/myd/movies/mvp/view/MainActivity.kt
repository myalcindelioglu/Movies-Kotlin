package com.myd.movies.mvp.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import com.myd.movies.R
import com.myd.movies.mvp.MainContract
import com.myd.movies.mvp.presenter.MainPresenter
import com.myd.movies.util.DateUtil
import com.myd.movies.util.RxUtil
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

/**
 * Created by MYD on 6/1/18.
 */
class MainActivity : DaggerAppCompatActivity(), MainContract.View {
    private val compositeDisposable = CompositeDisposable()

    var presenter: MainPresenter? = null
        @Inject set

    private var filterMenu: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.activity_main_toolbar)
        setSupportActionBar(toolbar)
        presenter = MainPresenter()
        presenter?.subscribe(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        filterMenu = menu.findItem(R.id.action_filter)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        presenter?.handleBackPress()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_filter) {
            presenter?.handleFilterClick()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        RxUtil.unSubscribe(compositeDisposable)
        presenter?.unSubscribe()
    }

    override fun subscribeMovieOnClick() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment)
        if (fragment is MovieListFragment) {
            compositeDisposable.add(
                    fragment.onClicks!!
                            .subscribe {
                                movieId :Int -> presenter?.handleOnMovieClick(movieId)
                            }
            )
        }
    }

    override fun showDetail(movieId: Int) {
//        val actionBar = supportActionBar
//        actionBar?.setDisplayHomeAsUpEnabled(true)
//        if (filterMenu != null) filterMenu!!.isVisible = false
//        val detailsFragment = MovieDetailsFragment.newInstance(movieId)
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment, detailsFragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
    }

    override fun showDatePicker() {
        val newFragment = DatePickerFragment()
        newFragment.setPresenter(presenter)
        newFragment.show(supportFragmentManager, "datePicker")
    }

    override fun showFilteredMovies(date: String) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment)
        if (fragment is MovieListFragment) {
            fragment.filterMovies(date)
        }
    }

    override fun showMovieList() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        if (filterMenu != null) filterMenu!!.isVisible = true
    }

    class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

        internal var presenter: MainPresenter? = null


        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            // Use the current date as the default date in the picker
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // Create a new instance of DatePickerDialog and return it
            return DatePickerDialog(activity!!, this, year, month, day)
        }

        override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {

            presenter?.filterMovies(DateUtil.intToString(year, month, day))
        }

        fun setPresenter(presenter: MainPresenter?) {
            this.presenter = presenter
        }
    }
}

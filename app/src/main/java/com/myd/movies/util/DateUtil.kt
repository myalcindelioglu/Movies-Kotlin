package com.myd.movies.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by MYD on 6/1/18.
 */
object DateUtil {
    private fun epochToString(epochDate: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date(epochDate))
    }

    fun intToString(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        return epochToString(calendar.timeInMillis)
    }
}
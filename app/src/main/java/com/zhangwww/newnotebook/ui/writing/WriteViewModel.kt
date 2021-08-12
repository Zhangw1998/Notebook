package com.zhangwww.newnotebook.ui.writing

import android.content.Context
import androidx.lifecycle.ViewModel
import com.zhangwww.newnotebook.R
import java.util.*

class WriteViewModel : ViewModel() {

    private var mTimestamp: Long = 0

    fun setDairyTimestamp(timestamp: Long) {
        this.mTimestamp = timestamp
    }


    fun getDairyData(context: Context) : String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = mTimestamp
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val isFirstSunday = calendar.firstDayOfWeek == Calendar.SUNDAY
        var weekday = calendar.get(Calendar.DAY_OF_WEEK)
        if (isFirstSunday) {
            weekday -= 1
        }
        val weekdayString = context.getString(getWeekdayResId(weekday))
        return context.getString(R.string.dairyDateFormat, year, month, day, weekdayString)
    }

    private fun getWeekdayResId(day: Int) : Int {
        return when (day) {
            0 -> R.string.Sunday
            1 -> R.string.Monday
            2 -> R.string.Tuesday
            3 -> R.string.Wednesday
            4 -> R.string.Thursday
            5 -> R.string.Friday
            6 -> R.string.Saturday
            else -> -1
        }
    }

    fun load() {

    }

    fun save() {

    }

}
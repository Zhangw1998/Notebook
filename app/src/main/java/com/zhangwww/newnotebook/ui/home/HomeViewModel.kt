package com.zhangwww.newnotebook.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhangwww.newnotebook.R
import com.zhangwww.newnotebook.data.DiaryCategoryModel
import java.util.*


class HomeViewModel : ViewModel() {

    private val mYearLiveData = MutableLiveData<Int>()
    private val mMonthLiveData = MutableLiveData<Int>()
    private val mDayLiveData = MutableLiveData<Int>()
    private val mMaxDay: Int

    val yearLiveData: LiveData<Int>
        get() = mYearLiveData

    val monthLiveData: LiveData<Int>
        get() = mMonthLiveData

    val dayLiveData: LiveData<Int>
        get() = mDayLiveData

    val currentTimestamp: Long
        get() {
            val calendar = Calendar.getInstance()
            yearLiveData.value?.let {
                calendar.set(Calendar.YEAR, it)
            }
            monthLiveData.value?.let {
                calendar.set(Calendar.MONTH, it - 1)
            }
            dayLiveData.value?.let {
                calendar.set(Calendar.DAY_OF_MONTH, it)
            }
            return calendar.timeInMillis
        }

    init {
        val calendar = Calendar.getInstance()
        mYearLiveData.value = calendar.get(Calendar.YEAR)
        mMonthLiveData.value = calendar.get(Calendar.MONTH) + 1
        mMaxDay = calendar.get(Calendar.DAY_OF_MONTH)
        mDayLiveData.value = mMaxDay
    }

    fun getDateList(): List<Int> = List(size = mDayLiveData.value ?: 0, init = { it + 1 })

    fun getCategoryList(): List<DiaryCategoryModel> {
        val cover1 = "https://t7.baidu.com/it/u=1819248061,230866778&fm=193&f=GIF"
        val cover2 = "https://t7.baidu.com/it/u=1297102096,3476971300&fm=193&f=GIF"
        val categoryModel1 = DiaryCategoryModel(0, "心情日记", cover1)
        val categoryModel2 = DiaryCategoryModel(1, "旅行日记", cover2)
        return listOf(categoryModel1, categoryModel2)
    }

    fun getTitle(context: Context, day: Int): String {
        return when (val count = mMaxDay - day) {
            0 -> context.getString(R.string.today)
            1 -> context.getString(R.string.yesterday)
            else -> context.getString(R.string.daysBefore, count)
        }
    }

    fun setSelectDay(day: Int) {
        mDayLiveData.value = day
    }
}
package com.zhangwww.newnotebook.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhangwww.newnotebook.data.DiaryCategoryModel
import java.util.*


class HomeViewModel : ViewModel() {

    private val mYearLiveData = MutableLiveData<Int>()
    private val mMonthLiveData = MutableLiveData<Int>()
    private val mDayLiveData = MutableLiveData<Int>()

    val yearLiveData: LiveData<Int>
        get() = mYearLiveData

    val monthLiveData: LiveData<Int>
        get() = mMonthLiveData

    val dayLiveData: LiveData<Int>
        get() = mDayLiveData

    init {
        val calendar = Calendar.getInstance()
        mYearLiveData.value = calendar.get(Calendar.YEAR)
        mMonthLiveData.value = calendar.get(Calendar.MONTH) + 1
        mDayLiveData.value = calendar.get(Calendar.DAY_OF_MONTH)
    }

//    fun getDateList(): List<Int> = List(size = mDayLiveData.value ?: 0, init = { it + 1 })
    fun getDateList(): List<Int> = List(size = 30 ?: 0, init = { it + 1 })

    fun getCategoryList(): List<DiaryCategoryModel> {
        val cover1 = "https://t7.baidu.com/it/u=1819248061,230866778&fm=193&f=GIF"
        val cover2 = "https://t7.baidu.com/it/u=1297102096,3476971300&fm=193&f=GIF"
        val categoryModel1 = DiaryCategoryModel(0, "心情日记", cover1)
        val categoryModel2 = DiaryCategoryModel(1, "旅行日记", cover2)
        return listOf(categoryModel1, categoryModel2)
    }

}
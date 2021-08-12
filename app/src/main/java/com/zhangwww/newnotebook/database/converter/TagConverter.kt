package com.zhangwww.newnotebook.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TagConverter {

    @TypeConverter
    fun stringToObject(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun objectToString(value: List<String>): String {
        return Gson().toJson(value)
    }

}
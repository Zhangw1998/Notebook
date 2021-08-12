package com.zhangwww.newnotebook.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.zhangwww.newnotebook.database.converter.TagConverter
import java.io.Serializable

@Entity
@TypeConverters(TagConverter::class)
data class DiaryModel(
    @PrimaryKey
    var id: String,
    var userId: String,
    var title: String,
    var content: String,
    var timestamp: Long,
    var timeZoneId: String,
    var tags: List<String>,
) : Serializable
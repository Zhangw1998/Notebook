package com.zhangwww.newnotebook.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class RecordModel(
    @PrimaryKey
    var id: String,
    var userId: String,
    var title: String,
    var content: String,
    var timestamp: Long
) : Serializable
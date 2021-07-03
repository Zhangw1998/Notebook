package com.zhangwww.newnotebook.database

import androidx.room.*
import com.zhangwww.newnotebook.data.RecordModel

@Dao
interface RecordDao {

    @Insert
    fun insert(record: RecordModel)

    @Delete
    fun delete(record: RecordModel)

    @Update
    fun update(record: RecordModel)

    @Query("SELECT * FROM RecordModel WHERE userId =:userId")
    fun queryRecordListByUserId(userId: String): List<RecordModel>

}
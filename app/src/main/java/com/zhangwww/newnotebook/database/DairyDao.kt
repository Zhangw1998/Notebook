package com.zhangwww.newnotebook.database

import androidx.room.*
import com.zhangwww.newnotebook.data.DiaryModel

@Dao
interface DairyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(diary: DiaryModel)

    @Delete
    suspend fun delete(diary: DiaryModel)

    @Update
    suspend fun update(diary: DiaryModel)

    @Query("SELECT * FROM DiaryModel WHERE userId =:userId")
    suspend fun queryRecordListByUserId(userId: String): List<DiaryModel>

}
package com.zhangwww.newnotebook.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zhangwww.newnotebook.App
import com.zhangwww.newnotebook.data.RecordModel

@Database(entities = [RecordModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recordDao(): RecordDao

    companion object {

        private const val DATABASE_NAME = "NoteBook"

        private val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(
                App.appContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

        fun recordDao() = instance.recordDao()


    }

}

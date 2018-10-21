package com.shekh.test.telstra.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shekh.test.telstra.model.Photo

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao

    companion object {
        private var databaseName = "TelstraTest.db"
        private var INSTANCE: AppDatabase? = null

        fun getDatabaseInstance(context: Context): AppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: Room.databaseBuilder(context,
                            AppDatabase::class.java,
                            databaseName)
                            .fallbackToDestructiveMigration()
                            .build()
                }.also { INSTANCE = it }
    }
}

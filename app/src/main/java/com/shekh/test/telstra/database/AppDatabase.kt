package com.shekh.test.telstra.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.shekh.test.telstra.model.Photo

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao

    companion object {
        private var databaseName = "TelstraTest.db"
        private var INSTANCE: AppDatabase? = null

        fun getDatabaseInstance(context: Context): AppDatabase =
                INSTANCE ?: synchronized(this) {
                INSTANCE ?:
                Room.databaseBuilder(context,
                        AppDatabase::class.java,
                        databaseName)
                        .fallbackToDestructiveMigration()
                        .build()
                }.also { INSTANCE = it }
    }
}

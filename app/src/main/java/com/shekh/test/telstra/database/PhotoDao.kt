package com.shekh.test.telstra.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shekh.test.telstra.model.Photo

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<Photo>)

    @Query("SELECT * FROM photo")
    fun getAllPhotos(): List<Photo>

    @Query("DELETE FROM photo")
    fun deleteAllPhotos()
}

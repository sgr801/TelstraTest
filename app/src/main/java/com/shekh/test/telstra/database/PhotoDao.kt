package com.shekh.test.telstra.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.shekh.test.telstra.model.Photo

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<Photo>)

    @Query("SELECT * FROM photo")
    fun getAllPhotos() : List<Photo>

    @Query("DELETE FROM photo")
    fun deleteAllPhotos()
}

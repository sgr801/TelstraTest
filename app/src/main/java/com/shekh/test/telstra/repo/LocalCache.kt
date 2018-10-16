package com.shekh.test.telstra.repo

import android.util.Log
import com.shekh.test.telstra.constants.AppConstants
import com.shekh.test.telstra.database.PhotoDao
import com.shekh.test.telstra.model.Photo
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete
import java.util.concurrent.Executor

/**
 * Class that handles the DAO local data source. This ensures that methods are triggered on the
 * correct executor.
 */
class LocalCache(private val photoDao: PhotoDao) {

    fun insert(photos: List<Photo>, insertFinished: () -> Unit) {
        doAsync {
            Log.d(AppConstants.LOG_TAG_FP, "Inserting: ${photos.size} photos")
            photoDao.insert(photos)
            onComplete {
                insertFinished()
            }
        }
    }

    fun getAllPhotos(): List<Photo> {
        return photoDao.getAllPhotos()
    }

    fun deleteAllPhotos() {
        doAsync {
            photoDao.deleteAllPhotos()
        }
    }

}

package com.shekh.test.telstra.repo

import android.util.Log
import com.shekh.test.telstra.database.AppDatabase
import com.shekh.test.telstra.model.Photo
import com.shekh.test.telstra.util.AppConstants
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete

/**
 * Class that handles the DAO local data source. This ensures that methods are triggered on the
 * correct executor.
 */
class LocalCache(appDatabase: AppDatabase) {

    private val photoDao = appDatabase.photoDao()

    fun insert(photos: List<Photo>, insertFinished: () -> Unit) {
        doAsync {
            Log.d(AppConstants.LOG_TAG_FP, "Inserting: ${photos.size} photos")
            photoDao.insert(photos)
            onComplete {
                insertFinished()
            }
        }
    }

    fun getAllPhotos(loadFinished: (List<Photo>) -> Unit) {
        doAsync {
            val list = photoDao.getAllPhotos()
            onComplete {
                loadFinished(list)
            }
        }
    }

    fun deleteAllPhotos() {
        doAsync {
            photoDao.deleteAllPhotos()
        }
    }

}

package com.shekh.test.telstra.repo

import android.content.Context
import com.shekh.test.telstra.R
import com.shekh.test.telstra.model.Photo
import com.shekh.test.telstra.model.PhotoResponse
import com.shekh.test.telstra.network.RequestQueueHelper
import com.shekh.test.telstra.network.RequestsProcessor
import com.shekh.test.telstra.util.AppPreferences

/**
 * Repository class to interact with API and local DB
 */
class AppRepository(private val context: Context, private val cache: LocalCache) {

    /**
     * Loads photos from API on successful response stores data to db
     * If can't fetch data from API will look at previously stored data in DB
     *
     * @param requestQueueHelper RequestQueueHelper
     * @param onSuccess Successful response callback with PhotoResponse
     * @param onFailure Unsuccessful response callback with error message
     */
    fun loadResponse(requestQueueHelper: RequestQueueHelper, onSuccess: (response: PhotoResponse) -> Unit, onFailed: (errMsg: String) -> Unit) {
        RequestsProcessor.fetchRowItems(
                context,
                requestQueueHelper,
                { response ->
                    response?.title?.let {
                        AppPreferences.savePreferences(context, AppPreferences.Key.TITLE_CACHE, it)
                    }
                    response?.rows?.let {
                        clearDatabase()
                        cache.insert(it) {
                            onSuccess(response)
                        }
                    }
                },
                { error ->
                    cache.getAllPhotos { photos ->
                        if (photos.isNotEmpty()) {
                            val title = AppPreferences.getString(context, AppPreferences.Key.TITLE_CACHE)?.let { it }
                                    ?: context.getString(R.string.default_title)
                            onSuccess(PhotoResponse(title, photos as ArrayList<Photo>))
                        } else {
                            onFailed(error)
                        }
                    }
                }
        )
    }

    fun clearDatabase() {
        cache.deleteAllPhotos()
    }

}

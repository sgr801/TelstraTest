package com.shekh.test.telstra.repo

import com.shekh.test.telstra.model.PhotoResponse
import com.shekh.test.telstra.network.RequestQueueHelper
import com.shekh.test.telstra.network.RequestsProcessor

class AppRepository(private val cache: LocalCache) {

    fun loadPhotos(requestQueueHelper: RequestQueueHelper, onSuccess: (response: PhotoResponse) -> Unit, onFailed: (errMsg: String) -> Unit) {
        RequestsProcessor.fetchRowItems(
                requestQueueHelper,
                { response ->
                    response?.rows?.let {
                        clearDatabase()
                        cache.insert(it) {
                            onSuccess(response)
                        }
                    }
                },
                { error ->
                    onFailed(error)
                }
        )
    }

    fun clearDatabase() {
        cache.deleteAllPhotos()
    }

}

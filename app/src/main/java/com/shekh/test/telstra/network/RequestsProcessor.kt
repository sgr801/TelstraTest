package com.shekh.test.telstra.network

import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import com.shekh.test.telstra.constants.AppConstants
import com.shekh.test.telstra.model.PhotoResponse

class RequestsProcessor {

    companion object {
        private const val LOG_TAG = AppConstants.LOG_TAG_FP

        fun fetchRowItems(requestQueueHelper: RequestQueueHelper,
                          onSuccess: (response: PhotoResponse?) -> Unit,
                          onFailure: (error: String) -> Unit) {
            try {
                val url = AppConstants.URL
                val objectRequest = JsonObjectRequest(
                        Request.Method.GET, url, null,
                        Response.Listener { response ->
                            val photosResponse: PhotoResponse? = Gson().fromJson(response.toString(), PhotoResponse::class.java)
                            onSuccess(photosResponse)
                        },
                        Response.ErrorListener { error ->
                            onFailure(error.message ?: "Unknown Error")
                        }
                )

                requestQueueHelper.addToRequestQueue(objectRequest)
            } catch (e: Exception) {
                Log.e(LOG_TAG, "Exception in makeRequest: " + e.toString())
                onFailure("Unknown Error")
            }
        }
    }
}

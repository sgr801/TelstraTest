package com.shekh.test.telstra.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import com.shekh.test.telstra.R
import com.shekh.test.telstra.model.PhotoResponse
import com.shekh.test.telstra.util.AppConstants

class RequestsProcessor {

    companion object {
        private const val LOG_TAG = AppConstants.LOG_TAG_FP

        fun fetchRowItems(context: Context,
                          requestQueueHelper: RequestQueueHelper,
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
                            onFailure(error.message ?: context.getString(R.string.unknown_error))
                        }
                )

                if (!requestQueueHelper.addToRequestQueue(objectRequest)) {
                    onFailure(context.getString(R.string.network_error_no_internet))
                }
            } catch (e: Exception) {
                Log.e(LOG_TAG, "Exception in makeRequest: " + e.toString())
                onFailure(context.getString(R.string.unknown_error))
            }
        }
    }
}

package com.shekh.test.telstra.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.shekh.test.telstra.R

class RequestQueueHelper(context: Context) {

    private var mContext = context
    private var mRequestQueue: RequestQueue = getRequestQueue()

    companion object {
        @SuppressLint("StaticFieldLeak")
        var mInstance: RequestQueueHelper? = null

        @Synchronized
        fun getInstance(context: Context): RequestQueueHelper {
            if (mInstance == null) {
                mInstance = RequestQueueHelper(context)
            }

            return mInstance!!
        }
    }

    private fun getRequestQueue(): RequestQueue {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.applicationContext)
        }

        return mRequestQueue
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo.isConnected
    }

    fun <T> addToRequestQueue(request: Request<T>) {
        if (isNetworkConnected()) {
            getRequestQueue().add(request)
        } else {
            Toast.makeText(mContext.applicationContext, mContext.getString(R.string.network_error_no_internet), Toast.LENGTH_LONG).show()
        }
    }

    fun cancelAllRequests() {
        getRequestQueue().cancelAll(mContext.applicationContext)
    }
}

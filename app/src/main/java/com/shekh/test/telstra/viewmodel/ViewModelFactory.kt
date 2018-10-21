package com.shekh.test.telstra.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shekh.test.telstra.network.RequestQueueHelper
import com.shekh.test.telstra.repo.AppRepository

class ViewModelFactory(private val requestQueueHelper: RequestQueueHelper, private val repository: AppRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(requestQueueHelper, repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

package com.shekh.test.telstra.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shekh.test.telstra.network.RequestQueueHelper
import com.shekh.test.telstra.repo.AppRepository

/**
 * Factory for ViewModels
 * Why do we need to do this? Read [this] (https://medium.com/@dpreussler/add-the-new-viewmodel-to-your-mvvm-36bfea86b159)
 */
class ViewModelFactory(private val requestQueueHelper: RequestQueueHelper, private val repository: AppRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(requestQueueHelper, repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

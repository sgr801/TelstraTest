package com.shekh.test.telstra.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.shekh.test.telstra.helpers.Injector
import com.shekh.test.telstra.network.RequestQueueHelper
import com.shekh.test.telstra.repo.AppRepository

/**
 * Factory for ViewModels
 * Why do we need to do this? Read [this] (https://medium.com/@dpreussler/add-the-new-viewmodel-to-your-mvvm-36bfea86b159)
 */
class ViewModelFactory(private val requestQueueHelper: RequestQueueHelper, private val repository: AppRepository) : ViewModelProvider.Factory {

    companion object {
        private var mViewModelFactoryInstance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
                mViewModelFactoryInstance ?: synchronized(this) {
                    mViewModelFactoryInstance ?:
                    ViewModelFactory(RequestQueueHelper.getInstance(context), Injector.provideAppRepository(context))
                }.also { mViewModelFactoryInstance = it }

    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(requestQueueHelper, repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

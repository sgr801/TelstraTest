package com.shekh.test.telstra.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shekh.test.telstra.model.PhotoResponse
import com.shekh.test.telstra.network.RequestQueueHelper
import com.shekh.test.telstra.repo.AppRepository

class MainViewModel(private val requestQueueHelper: RequestQueueHelper, private val repository: AppRepository) : ViewModel() {

    private val photosMutable = MutableLiveData<PhotoResponse>()
    private val errMutable = MutableLiveData<String>()
    val photos: LiveData<PhotoResponse> = photosMutable
    val networkError: LiveData<String> = errMutable

    fun getPhotos() {
        repository.loadResponse(requestQueueHelper, {
            photosMutable.value = it
        }, {
            errMutable.value = it
        })
    }

}

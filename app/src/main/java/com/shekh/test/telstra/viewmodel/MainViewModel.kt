package com.shekh.test.telstra.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.shekh.test.telstra.model.PhotoResponse
import com.shekh.test.telstra.network.RequestQueueHelper
import com.shekh.test.telstra.repo.AppRepository

class MainViewModel(private val requestQueueHelper: RequestQueueHelper, private val repository: AppRepository) : ViewModel() {

//    private val photosLiveData = MutableLiveData<ResponseModel>()
//    private val photoResult: LiveData<ResponseResult> = Transformations.map(photosLiveData, {repository.loadPhotos(requestQueueHelper)})

    // List
    private val photosMutable = MutableLiveData<PhotoResponse>()
    val errMutable = MutableLiveData<String>()
    val photos: LiveData<PhotoResponse> = photosMutable
    val networkError: LiveData<String> = errMutable

    fun getPhotos() {
        repository.loadPhotos(requestQueueHelper, {
            photosMutable.value = it
        }, {
            errMutable.value = it
        })
    }

}

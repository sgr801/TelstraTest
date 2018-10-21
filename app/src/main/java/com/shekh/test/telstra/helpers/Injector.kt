package com.shekh.test.telstra.helpers

import android.content.Context
import com.shekh.test.telstra.database.AppDatabase
import com.shekh.test.telstra.network.RequestQueueHelper
import com.shekh.test.telstra.repo.AppRepository
import com.shekh.test.telstra.repo.LocalCache
import com.shekh.test.telstra.viewmodel.ViewModelFactory

object Injector {

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val requestQueueHelper = RequestQueueHelper.getInstance(context)
        return ViewModelFactory(requestQueueHelper, provideAppRepository(context))
    }

    fun provideAppRepository(context: Context): AppRepository {
        return AppRepository(context, provideCache(context))
    }

    fun provideCache(context: Context): LocalCache {
        val database: AppDatabase = AppDatabase.getDatabaseInstance(context)
        return LocalCache(database)
    }
}

package com.shekh.test.telstra.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Photo(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var title: String?,
        var description: String?,
        var imageHref: String?
)
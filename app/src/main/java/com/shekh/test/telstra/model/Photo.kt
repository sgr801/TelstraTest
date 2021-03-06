package com.shekh.test.telstra.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var title: String?,
        var description: String?,
        var imageHref: String?
)
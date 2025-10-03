package com.siddhesh.pixelpdf.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val fileType: String,
    val progress: Int,
    val coverUrl: String?,
    val fileUri: String,
    val fileSize: Long,
    val pages: Int,
    val lastOpened: Long,
    val isFavorite: Boolean,
    val dateAdded: Long
)

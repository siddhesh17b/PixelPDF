package com.siddhesh.pixelpdf.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading_progress")
data class ReadingProgressEntity(
    @PrimaryKey val bookId: String,
    val currentPage: Int,
    val totalPages: Int,
    val scrollPosition: Float,
    val lastUpdated: Long
)

package com.siddhesh.pixelpdf.data

data class ReadingProgress(
    val bookId: String,
    val currentPage: Int,
    val totalPages: Int,
    val scrollPosition: Float = 0f, // For continuous mode
    val lastUpdated: Long // timestamp
)

package com.siddhesh.pixelpdf.data

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val fileType: FileType, // enum: PDF, EPUB
    val progress: Int, // 0-100 percentage
    val coverUrl: String?, // Local file path or URI
    val fileUri: String, // Content URI or file path
    val fileSize: Long, // in bytes
    val pages: Int,
    val lastOpened: Long, // timestamp
    val isFavorite: Boolean = false,
    val dateAdded: Long // timestamp
)

enum class FileType {
    PDF, EPUB
}

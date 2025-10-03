package com.siddhesh.pixelpdf.data

data class Annotation(
    val id: String,
    val bookId: String,
    val type: AnnotationType,
    val text: String,
    val note: String? = null,
    val pageNumber: Int,
    val startOffset: Int,
    val endOffset: Int,
    val color: Int, // Highlight color
    val createdAt: Long,
    val updatedAt: Long
)

enum class AnnotationType {
    HIGHLIGHT, NOTE, BOOKMARK
}

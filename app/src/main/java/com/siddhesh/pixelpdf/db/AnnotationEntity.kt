package com.siddhesh.pixelpdf.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "annotations")
data class AnnotationEntity(
    @PrimaryKey val id: String,
    val bookId: String,
    val type: String,
    val text: String,
    val note: String?,
    val pageNumber: Int,
    val startOffset: Int,
    val endOffset: Int,
    val color: Int,
    val createdAt: Long,
    val updatedAt: Long
)

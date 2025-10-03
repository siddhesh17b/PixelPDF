package com.siddhesh.pixelpdf.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class, AnnotationEntity::class, ReadingProgressEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun annotationDao(): AnnotationDao
    abstract fun readingProgressDao(): ReadingProgressDao
}

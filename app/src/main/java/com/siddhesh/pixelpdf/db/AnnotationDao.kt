package com.siddhesh.pixelpdf.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AnnotationDao {
    @Query("SELECT * FROM annotations WHERE bookId = :bookId ORDER BY pageNumber, startOffset")
    fun getAnnotationsForBook(bookId: String): Flow<List<AnnotationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnnotation(annotation: AnnotationEntity)

    @Update
    suspend fun updateAnnotation(annotation: AnnotationEntity)

    @Delete
    suspend fun deleteAnnotation(annotation: AnnotationEntity)
}

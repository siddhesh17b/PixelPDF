package com.siddhesh.pixelpdf.db.data

import com.siddhesh.pixelpdf.db.BookDao
import com.siddhesh.pixelpdf.db.BookEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepository @Inject constructor(private val bookDao: BookDao) {

    fun getBooks(): Flow<List<BookEntity>> = bookDao.getBooks()

    fun getBookById(bookId: String): Flow<BookEntity?> = bookDao.getBookById(bookId)

    suspend fun insertBook(book: BookEntity) {
        bookDao.insertBook(book)
    }
}

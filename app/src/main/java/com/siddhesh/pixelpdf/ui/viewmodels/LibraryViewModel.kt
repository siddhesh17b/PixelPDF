package com.siddhesh.pixelpdf.ui.viewmodels

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siddhesh.pixelpdf.data.FileType
import com.siddhesh.pixelpdf.db.BookEntity
import com.siddhesh.pixelpdf.db.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    val books: StateFlow<List<BookEntity>> = bookRepository.getBooks()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun importBook(uri: Uri, context: Context) {
        viewModelScope.launch {
            val contentResolver = context.contentResolver
            var fileName = ""
            var fileSize = 0L
            contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                    if (nameIndex != -1) {
                        fileName = cursor.getString(nameIndex)
                    }
                    if (sizeIndex != -1) {
                        fileSize = cursor.getLong(sizeIndex)
                    }
                }
            }

            val fileType = when {
                fileName.endsWith(".pdf", true) -> FileType.PDF.name
                fileName.endsWith(".epub", true) -> FileType.EPUB.name
                else -> return@launch // Unsupported file type
            }

            val book = BookEntity(
                id = UUID.randomUUID().toString(),
                title = fileName.substringBeforeLast('.'),
                author = "Unknown Author", // Placeholder
                fileType = fileType,
                progress = 0,
                coverUrl = null, // Placeholder
                fileUri = uri.toString(),
                fileSize = fileSize,
                pages = 0, // Placeholder
                lastOpened = System.currentTimeMillis(),
                isFavorite = false,
                dateAdded = System.currentTimeMillis()
            )

            bookRepository.insertBook(book)
        }
    }
}

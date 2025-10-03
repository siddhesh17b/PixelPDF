package com.siddhesh.pixelpdf.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siddhesh.pixelpdf.db.BookEntity
import com.siddhesh.pixelpdf.db.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ReaderViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val bookId: String = savedStateHandle.get<String>("bookId")!!

    val book: StateFlow<BookEntity?> = bookRepository.getBookById(bookId)
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
}

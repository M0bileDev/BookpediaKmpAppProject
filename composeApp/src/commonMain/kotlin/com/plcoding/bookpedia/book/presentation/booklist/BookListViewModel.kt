package com.plcoding.bookpedia.book.presentation.booklist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookListViewModel : ViewModel() {

    private val _state = MutableStateFlow(BookListState.createDefaultState())
    val state = _state.asStateFlow()
}
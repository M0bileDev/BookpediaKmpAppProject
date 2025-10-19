package com.plcoding.bookpedia.book.presentation.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookListViewModel : ViewModel() {

    private val _state = MutableStateFlow(BookListState.createDefaultState())
    val state get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<BookListViewModelAction>()
    val action get() = _action.asSharedFlow()

    fun onAction(action: BookListScreenAction) = when (action) {
        is BookListScreenAction.OnBookClick -> {
            viewModelScope.launch {
                ensureActive()
                _action.emit(BookListViewModelAction.OnNavigateToBookDetails(action.book))
            }
        }

        is BookListScreenAction.OnSearchQueryChanged -> {
            _state.update {
                it.copy(searchQuery = action.query)
            }
        }

        is BookListScreenAction.OnTabSelected -> {
            _state.update {
                it.copy(selectedTabIndex = action.index)
            }
        }
    }
}


package com.plcoding.bookpedia.book.presentation.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.core.domain.onError
import com.plcoding.bookpedia.core.domain.onSuccess
import com.plcoding.bookpedia.core.presentation.toUiText
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookListViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {

    private var cachedBooks = emptyList<Book>()
    private var searchJob: Job? = null

    private val _state = MutableStateFlow(BookListState.createDefaultState())
    val state = _state.onStart {
            if (cachedBooks.isEmpty()) {
                observeSearchQuery()
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            _state.value
        )

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

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        state.map {
            it.searchQuery
        }.distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                errorMessage = null,
                                searchResult = cachedBooks
                            )
                        }
                    }

                    query.length >= 3 -> {
                        searchJob?.cancel()
                        searchJob = searchBooks(query)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            bookRepository.searchBooks(query)
                .onSuccess { searchResults ->
                    cachedBooks = searchResults
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            searchResult = searchResults
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.toUiText(),
                            searchResult = emptyList()
                        )
                    }
                }
        }
    }
}


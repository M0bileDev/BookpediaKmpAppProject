package com.plcoding.bookpedia.book.presentation.bookdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.plcoding.bookpedia.app.Route
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.core.domain.onSuccess
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailsViewModel(
    private val bookRepository: BookRepository,
    private val saveStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(BookDetailsState.createDefault())
    val state = _state.onStart {
        fetchBookDescription()
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        _state.value
    )

    private val bookWorkId
        get() = saveStateHandle.toRoute<Route.Book.BookDetails>().bookId

    private val _action = MutableSharedFlow<BookDetailsViewModelAction>()
    val action get() = _action.asSharedFlow()

    fun onAction(action: BookDetailsAction) {
        when (action) {
            is BookDetailsAction.OnBackClick -> {
                viewModelScope.launch {
                    ensureActive()
                    _action.emit(BookDetailsViewModelAction.OnNavigateBack)
                }
            }

            is BookDetailsAction.OnFavoriteClick -> {}
            is BookDetailsAction.OnSelectedBookChanged -> {
                _state.update {
                    it.copy(
                        book = action.book
                    )
                }
            }
        }
    }

    private fun fetchBookDescription() {
        viewModelScope.launch {
            bookRepository.getBookDescription(
                bookWorkId
            ).onSuccess { description ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        book = it.book?.copy(
                            description = description
                        )
                    )
                }
            }
        }
    }
}
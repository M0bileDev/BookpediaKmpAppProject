package com.plcoding.bookpedia.book.presentation.bookdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailsViewModel : ViewModel() {

    private val _state = MutableStateFlow(BookDetailsState.createDefault())
    val state = _state.asStateFlow()

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
                _state.update { it.copy(
                    book = action.book
                ) }
            }
        }
    }
}
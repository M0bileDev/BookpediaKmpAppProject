package com.plcoding.bookpedia.book.presentation.bookdetails

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookDetailsViewModel : ViewModel() {

    private val _state = MutableStateFlow(BookDetailsState.createDefault())
    val state = _state.asStateFlow()

    fun onAction(bookDetailsAction: BookDetailsAction){
        // TODO: Not implemented yet
    }
}
package com.plcoding.bookpedia.book.presentation.bookdetails

import com.plcoding.bookpedia.book.domain.Book

sealed interface BookDetailsAction {
    data object OnBackClick : BookDetailsAction
    data object OnFavoriteClick : BookDetailsAction
    data class OnSelectedBookChanged(val book: Book) : BookDetailsAction
}
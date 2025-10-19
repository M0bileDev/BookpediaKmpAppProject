package com.plcoding.bookpedia.book.presentation.booklist

import com.plcoding.bookpedia.book.domain.Book

sealed interface BookListScreenAction {
    data class OnSearchQueryChanged(val query: String) : BookListScreenAction
    data class OnBookClick(val book: Book) : BookListScreenAction
    data class OnTabSelected(val index: Int) : BookListScreenAction
}
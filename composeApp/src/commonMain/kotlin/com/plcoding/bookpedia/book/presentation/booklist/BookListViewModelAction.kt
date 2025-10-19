package com.plcoding.bookpedia.book.presentation.booklist

import com.plcoding.bookpedia.book.domain.Book

sealed interface BookListViewModelAction {
    data class OnNavigateToBookDetails(val book: Book) : BookListViewModelAction
}
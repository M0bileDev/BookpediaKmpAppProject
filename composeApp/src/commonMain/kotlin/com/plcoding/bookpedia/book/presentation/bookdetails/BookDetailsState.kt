package com.plcoding.bookpedia.book.presentation.bookdetails

import com.plcoding.bookpedia.book.domain.Book

data class BookDetailsState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)
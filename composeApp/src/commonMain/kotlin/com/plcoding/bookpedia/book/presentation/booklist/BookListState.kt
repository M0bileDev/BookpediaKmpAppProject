package com.plcoding.bookpedia.book.presentation.booklist

import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.presentation.UiText

@ConsistentCopyVisibility
data class BookListState private constructor(
    val searchQuery: String = "",
    val searchResult: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
) {
    companion object {
        fun createDefaultState() = BookListState()
    }
}

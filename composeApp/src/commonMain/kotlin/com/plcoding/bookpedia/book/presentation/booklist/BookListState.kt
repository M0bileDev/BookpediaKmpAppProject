package com.plcoding.bookpedia.book.presentation.booklist

import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "",
    val searchResult: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
) {
    companion object {
        fun createDefaultState() = BookListState()
        fun previewBooks() = (1..100).map {
            Book(
                id = it.toString(),
                title = "Book $it",
                "https://test.com",
                authors = listOf("Lorem ipsum"),
                description = "Description $it",
                languages = listOf("Lorem ipsum"),
                firstPublicationYear = "2000",
                averageRating = 4.7564,
                ratingCount = 6,
                numPages = 200,
                numEdition = 2

            )
        }
    }
}

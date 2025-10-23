package com.plcoding.bookpedia.book.presentation.booklist.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.plcoding.bookpedia.book.domain.Book

@Composable
fun BookList(
    modifier: Modifier = Modifier,
    books: List<Book>,
    onClick: (Book) -> Unit,
    scrollState: ScrollState = rememberScrollState()
) {

}
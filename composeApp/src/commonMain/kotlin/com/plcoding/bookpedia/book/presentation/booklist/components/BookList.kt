package com.plcoding.bookpedia.book.presentation.booklist.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.plcoding.bookpedia.book.domain.Book

@Composable
fun BookList(
    modifier: Modifier = Modifier,
    books: List<Book>,
    onClick: (Book) -> Unit,
    scrollState: LazyListState = rememberLazyListState()
) {

}
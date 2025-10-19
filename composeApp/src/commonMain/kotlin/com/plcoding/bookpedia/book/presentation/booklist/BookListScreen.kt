package com.plcoding.bookpedia.book.presentation.booklist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.bookpedia.book.domain.Book
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BookListScreen(
    modifier: Modifier = Modifier,
    viewModel: BookListViewModel = koinViewModel(),
    onBookClick: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    BookListScreen(
        modifier = modifier,
        state = state,
        onAction = {}
    )
}

@Composable
fun BookListScreen(
    modifier: Modifier,
    state: BookListState,
    onAction: (Book) -> Unit,
) {

}
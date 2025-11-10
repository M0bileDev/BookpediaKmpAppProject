package com.plcoding.bookpedia.book.presentation.bookdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BookDetailsScreen(
    modifier: Modifier,
    viewModel: BookDetailsViewModel,
    onBackClick: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    BookDetailsScreen(
        bookDetailsState = state,
        onAction = { action ->
            when (action) {
                is BookDetailsAction.OnBackClick -> onBackClick()
                else -> Unit
            }
        }
    )
}

@Composable
fun BookDetailsScreen(
    bookDetailsState: BookDetailsState,
    onAction: (BookDetailsAction) -> Unit
) {

}
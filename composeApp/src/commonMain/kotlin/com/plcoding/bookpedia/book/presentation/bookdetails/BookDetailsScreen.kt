package com.plcoding.bookpedia.book.presentation.bookdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun BookDetailsScreen(
    modifier: Modifier,
    viewModel: BookDetailsViewModel,
    onBackClick: () -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycle = LocalLifecycleOwner.current

    LaunchedEffect(lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.action.distinctUntilChanged().collect { action ->
                when (action) {
                    BookDetailsViewModelAction.OnNavigateBack -> onBackClick()
                }
            }
        }
    }

    BookDetailsScreen(
        modifier = modifier,
        bookDetailsState = state,
        onAction = { action ->
            viewModel.onAction(action)
        },
    )
}

@Composable
fun BookDetailsScreen(
    modifier: Modifier,
    bookDetailsState: BookDetailsState,
    onAction: (BookDetailsAction) -> Unit,
) {

}
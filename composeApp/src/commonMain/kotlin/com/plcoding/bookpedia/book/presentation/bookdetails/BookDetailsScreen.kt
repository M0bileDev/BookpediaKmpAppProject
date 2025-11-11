package com.plcoding.bookpedia.book.presentation.bookdetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.plcoding.bookpedia.book.presentation.bookdetails.components.BlurredImageBackground
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun BookDetailsScreen(
    modifier: Modifier = Modifier,
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
        modifier = modifier.fillMaxSize(),
        state = state,
        onAction = { action ->
            viewModel.onAction(action)
        },
    )
}

@Composable
fun BookDetailsScreen(
    modifier: Modifier,
    state: BookDetailsState,
    onAction: (BookDetailsAction) -> Unit,
) {
    BlurredImageBackground(
        modifier,
        imageUrl = state.book?.imageUrl,
        isFavorite = state.isFavorite,
        onFavoriteClick = { onAction(BookDetailsAction.OnFavoriteClick) },
        onBackClick = { onAction(BookDetailsAction.OnBackClick) },
    ) {

    }
}
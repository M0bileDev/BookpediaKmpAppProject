package com.plcoding.bookpedia.book.presentation.booklist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.plcoding.bookpedia.book.domain.Book
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BookListScreen(
    modifier: Modifier = Modifier,
    viewModel: BookListViewModel = koinViewModel(),
    navigateToBookDetails: (Book) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.action.distinctUntilChanged().collect { action ->
                when (action) {
                    is BookListViewModelAction.OnNavigateToBookDetails -> {
                        navigateToBookDetails(action.book)
                    }
                }
            }
        }
    }

    BookListScreen(
        modifier = modifier,
        state = state,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}

@Composable
fun BookListScreen(
    modifier: Modifier,
    state: BookListState,
    onAction: (BookListScreenAction) -> Unit,
) {

}
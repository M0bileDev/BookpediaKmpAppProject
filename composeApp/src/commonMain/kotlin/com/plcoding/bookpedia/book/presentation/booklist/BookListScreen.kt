package com.plcoding.bookpedia.book.presentation.booklist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.presentation.booklist.components.SearchBar
import com.plcoding.bookpedia.core.presentation.DarkBlue
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.compose.ui.tooling.preview.Preview
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
    modifier: Modifier = Modifier,
    state: BookListState,
    onAction: (BookListScreenAction) -> Unit,
) {
    val keyboard = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier.fillMaxWidth().background(DarkBlue).statusBarsPadding()
    ) {
        SearchBar(
            modifier = Modifier.widthIn(max = 400.dp).fillMaxWidth().padding(16.dp),
            searchQuery = state.searchQuery,
            onSearchQueryChanged = { search ->
                onAction(BookListScreenAction.OnSearchQueryChanged(search))
            },
            onImeDone = {
                keyboard?.hide()
            }
        )
    }
}

@Preview
@Composable
fun PreviewBookListScreen() {
    MaterialTheme {
        BookListScreen(
            state = BookListState.createDefaultState(),
            onAction = {}
        )
    }
}
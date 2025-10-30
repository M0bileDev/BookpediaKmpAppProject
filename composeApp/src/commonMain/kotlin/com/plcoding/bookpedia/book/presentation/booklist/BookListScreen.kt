package com.plcoding.bookpedia.book.presentation.booklist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.favorites
import cmp_bookpedia.composeapp.generated.resources.search_results
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.presentation.booklist.components.SearchBar
import com.plcoding.bookpedia.core.presentation.DarkBlue
import com.plcoding.bookpedia.core.presentation.SandYellow
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.compose.resources.stringResource
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
        modifier = modifier.fillMaxSize().background(DarkBlue).statusBarsPadding()
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
        Surface(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PrimaryTabRow(
                    selectedTabIndex = state.selectedTabIndex,
                    modifier = Modifier.widthIn(700.dp).fillMaxWidth(),
                    indicator = {
                        TabRowDefaults.PrimaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(
                                state.selectedTabIndex,
                                matchContentSize = false
                            ),
                            width = Dp.Unspecified,
                            color = SandYellow
                        )
                    }
                ) {
                    Tab(
                        modifier = Modifier.padding(vertical = 16.dp),
                        selected = state.selectedTabIndex == 0,
                        onClick = { onAction(BookListScreenAction.OnTabSelected(0)) },
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f),
                    ) {
                        Text(text = stringResource(Res.string.search_results))
                    }
                    Tab(
                        modifier = Modifier.padding(vertical = 16.dp),
                        selected = state.selectedTabIndex == 1,
                        onClick = { onAction(BookListScreenAction.OnTabSelected(1)) },
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(text = stringResource(Res.string.favorites))
                    }
                }
            }
        }
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
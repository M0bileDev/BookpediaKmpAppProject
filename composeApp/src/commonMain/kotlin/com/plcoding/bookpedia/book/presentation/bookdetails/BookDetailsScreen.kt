package com.plcoding.bookpedia.book.presentation.bookdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.description_not_available
import cmp_bookpedia.composeapp.generated.resources.empty_x
import cmp_bookpedia.composeapp.generated.resources.languages
import cmp_bookpedia.composeapp.generated.resources.pages
import cmp_bookpedia.composeapp.generated.resources.rating
import cmp_bookpedia.composeapp.generated.resources.star_24px
import cmp_bookpedia.composeapp.generated.resources.star_hint
import cmp_bookpedia.composeapp.generated.resources.synopsis
import com.plcoding.bookpedia.book.presentation.bookdetails.components.BlurredImageBackground
import com.plcoding.bookpedia.book.presentation.bookdetails.components.BookChip
import com.plcoding.bookpedia.book.presentation.bookdetails.components.ChipSize
import com.plcoding.bookpedia.book.presentation.bookdetails.components.TitledContent
import com.plcoding.bookpedia.core.presentation.SandYellow
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.floor

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
        state.book?.let {
            Column(
                modifier = Modifier
                    .widthIn(max = 700.dp)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.book.title,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = state.book.authors.joinToString(),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier.padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    state.book.averageRating?.let { rating ->
                        TitledContent(
                            title = stringResource(Res.string.rating),
                        ) {
                            BookChip {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = stringResource(
                                            Res.string.empty_x,
                                            floor(rating).toInt().toString()
                                        )
                                    )
                                    Icon(
                                        painter = painterResource(Res.drawable.star_24px),
                                        contentDescription = stringResource(Res.string.star_hint),
                                        tint = SandYellow
                                    )
                                }
                            }
                        }
                    } ?: Unit

                    state.book.numPages?.let { pages ->
                        TitledContent(
                            title = stringResource(Res.string.pages),
                        ) {
                            BookChip {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = stringResource(
                                            Res.string.empty_x,
                                            floor(pages.toDouble()).toInt().toString()
                                        )
                                    )
                                }
                            }
                        }
                    } ?: Unit
                }

                state.book.languages?.let { languages ->
                    if (languages.isNotEmpty()) {
                        TitledContent(
                            modifier = Modifier.padding(vertical = 8.dp),
                            title = stringResource(Res.string.languages)
                        ) {
                            FlowRow(
                                modifier = Modifier.wrapContentSize(Alignment.Center),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                languages.forEach {
                                    BookChip(
                                        modifier = Modifier.padding(2.dp),
                                        chipSize = ChipSize.SMALL
                                    ) {
                                        Text(
                                            text = it.uppercase(),
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        Unit
                    }
                } ?: Unit

                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 24.dp, bottom = 8.dp),
                    text = stringResource(Res.string.synopsis),
                    style = MaterialTheme.typography.titleLarge
                )

                if (state.isLoading) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = state.book.description
                            ?: stringResource(
                                Res.string.description_not_available
                            ),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        } ?: Unit

    }
}
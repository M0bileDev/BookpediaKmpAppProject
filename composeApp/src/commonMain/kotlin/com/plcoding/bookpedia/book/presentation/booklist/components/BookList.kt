package com.plcoding.bookpedia.book.presentation.booklist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plcoding.bookpedia.book.domain.Book
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BookList(
    modifier: Modifier = Modifier,
    books: List<Book>,
    onClick: (Book) -> Unit,
    scrollState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        modifier = modifier,
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(
            items = books,
            key = { book ->
                book.id
            }
        ) { book ->
            BookListItem(
                modifier = Modifier.widthIn(max = 700.dp).fillMaxWidth(),
                book = book,
                onClick = {
                    onClick(book)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewBookList() {
    MaterialTheme {
        BookList(
            modifier = Modifier.fillMaxWidth(),
            books = listOf(
                Book(
                    "0",
                    "Kotlin",
                    "",
                    listOf("Lorem ipsum"),
                    "Lorem ipsum",
                    emptyList(),
                    "2000",
                    6.0,
                    100,
                    420,
                    1
                ),
                Book(
                    "1",
                    "Kotlin 2.2.21",
                    "",
                    listOf("Lorem ipsum"),
                    "Lorem ipsum",
                    emptyList(),
                    "2000",
                    6.0,
                    100,
                    420,
                    1
                )
            ),
            onClick = {}
        )
    }
}
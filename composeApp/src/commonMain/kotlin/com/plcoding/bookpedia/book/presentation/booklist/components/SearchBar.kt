package com.plcoding.bookpedia.book.presentation.booklist.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.search_hint
import com.plcoding.bookpedia.core.presentation.DarkBlue
import com.plcoding.bookpedia.core.presentation.SandYellow
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onImeSearch: () -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChanged,
        shape = RoundedCornerShape(100),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = DarkBlue,
            focusedBorderColor = SandYellow
        ),
        placeholder = {
            Text(text = stringResource(Res.string.search_hint))
        }
    )
}

@Preview(name = "search_without_value")
@Composable
fun PreviewSearchBar() {
    MaterialTheme {
        SearchBar(
            Modifier,
            "Kotlin",
            onSearchQueryChanged = {},
            onImeSearch = {}
        )
    }
}

@Preview(name = "search_with_value")
@Composable
fun PreviewSearchBar2() {
    MaterialTheme {
        SearchBar(
            Modifier,
            "",
            onSearchQueryChanged = {},
            onImeSearch = {}
        )
    }
}
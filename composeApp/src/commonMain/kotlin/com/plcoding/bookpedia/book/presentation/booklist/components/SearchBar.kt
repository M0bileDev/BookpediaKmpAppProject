package com.plcoding.bookpedia.book.presentation.booklist.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.clear_search_hint
import cmp_bookpedia.composeapp.generated.resources.close_24px
import cmp_bookpedia.composeapp.generated.resources.search_24px
import cmp_bookpedia.composeapp.generated.resources.search_hint
import cmp_bookpedia.composeapp.generated.resources.search_leading_icon
import com.plcoding.bookpedia.core.presentation.DarkBlue
import com.plcoding.bookpedia.core.presentation.SandYellow
import org.jetbrains.compose.resources.painterResource
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
        modifier = modifier,
        value = searchQuery,
        onValueChange = onSearchQueryChanged,
        shape = RoundedCornerShape(100),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = DarkBlue,
            focusedBorderColor = SandYellow
        ),
        placeholder = {
            Text(text = stringResource(Res.string.search_hint))
        },
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.search_24px),
                contentDescription = stringResource(Res.string.search_leading_icon),
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = {
                onImeSearch()
            }
        ),
        trailingIcon = {
            AnimatedVisibility(
                visible = searchQuery.isNotBlank()
            ) {
                IconButton(
                    onClick = { onSearchQueryChanged("") }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.close_24px),
                        contentDescription = stringResource(Res.string.clear_search_hint)
                    )
                }
            }
        }
    )
}

@Preview(name = "search_without_value")
@Composable
fun PreviewSearchBar() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            SearchBar(
                Modifier,
                "Kotlin",
                onSearchQueryChanged = {},
                onImeSearch = {}
            )
        }
    }
}

@Preview(name = "search_with_value")
@Composable
fun PreviewSearchBar2() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            SearchBar(
                Modifier,
                "",
                onSearchQueryChanged = {},
                onImeSearch = {},
            )
        }

    }
}
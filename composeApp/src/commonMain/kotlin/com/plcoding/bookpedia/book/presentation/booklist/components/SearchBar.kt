package com.plcoding.bookpedia.book.presentation.booklist.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.clear_search_hint
import cmp_bookpedia.composeapp.generated.resources.close_24px
import cmp_bookpedia.composeapp.generated.resources.search_24px
import cmp_bookpedia.composeapp.generated.resources.search_hint
import cmp_bookpedia.composeapp.generated.resources.search_leading_icon
import com.plcoding.bookpedia.core.presentation.DarkBlue
import com.plcoding.bookpedia.core.presentation.DesertWhite
import com.plcoding.bookpedia.core.presentation.SandYellow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onImeDone: () -> Unit,
    backgroundColor: Color = DesertWhite,
    shape: Shape = RoundedCornerShape(100),
    cursorColor: Color = DarkBlue,
    focusedBorderColor: Color = SandYellow,
    iconsTint: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
) {
    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = SandYellow,
            backgroundColor = SandYellow
        )
    ) {
        OutlinedTextField(
            modifier = modifier.background(backgroundColor, shape = shape)
                .minimumInteractiveComponentSize(),
            value = searchQuery,
            onValueChange = onSearchQueryChanged,
            shape = shape,
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = cursorColor,
                focusedBorderColor = focusedBorderColor
            ),
            placeholder = {
                Text(text = stringResource(Res.string.search_hint))
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.search_24px),
                    contentDescription = stringResource(Res.string.search_leading_icon),
                    tint = iconsTint
                )
            },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    onImeDone()
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
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
                            contentDescription = stringResource(Res.string.clear_search_hint),
                            tint = iconsTint
                        )
                    }
                }
            })
    }
}

@Preview(name = "search_without_value")
@Composable
fun PreviewSearchBar() {
    MaterialTheme {
        SearchBar(
            Modifier,
            "Kotlin",
            onSearchQueryChanged = {},
            onImeDone = {}
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
            onImeDone = {},
        )
    }
}
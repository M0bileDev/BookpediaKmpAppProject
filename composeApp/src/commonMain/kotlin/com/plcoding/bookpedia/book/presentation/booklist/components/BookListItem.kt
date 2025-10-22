package com.plcoding.bookpedia.book.presentation.booklist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.presentation.LightBlue

@Composable
fun BookListItem(
    modifier: Modifier = Modifier,
    book: Book,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier.clickable(onClick = onClick).background(LightBlue.copy(alpha = 0.2f)),
        shape = RoundedCornerShape(32.dp)
    ) {

    }
}
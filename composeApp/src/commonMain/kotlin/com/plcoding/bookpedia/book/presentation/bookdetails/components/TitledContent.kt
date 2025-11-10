package com.plcoding.bookpedia.book.presentation.bookdetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TitledContent(
    modifier: Modifier,
    horizontal: Alignment.Horizontal = Alignment.CenterHorizontally,
    title: String,
    titleStyle: TextStyle = MaterialTheme.typography.titleSmall,
    contentPadding: Dp = 8.dp,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontal,
    ) {
        Text(
            text = title,
            style = titleStyle
        )
        Spacer(modifier = Modifier.height(contentPadding))
        content()
    }
}
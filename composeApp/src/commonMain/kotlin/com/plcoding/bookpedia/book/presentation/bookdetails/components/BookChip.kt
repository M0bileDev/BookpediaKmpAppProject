package com.plcoding.bookpedia.book.presentation.bookdetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plcoding.bookpedia.core.presentation.LightBlue

enum class ChipSize(val dpSize: Dp) {
    SMALL(50.dp),
    REGULAR(80.dp)
}

@Composable
fun BookChip(
    modifier: Modifier,
    chipSize: ChipSize = ChipSize.REGULAR,
    backgroundColor: Color = LightBlue,
    verticalPadding: Dp = 8.dp,
    horizontalPadding: Dp = 12.dp,
    cornerShapeSize: Dp = 16.dp,
    contentAlignment: Alignment = Alignment.Center,
    chipContent: @Composable () -> Unit
) {
    Box(
        modifier = modifier.widthIn(
            min = chipSize.dpSize
        ).clip(
            RoundedCornerShape(cornerShapeSize)
        )
            .background(backgroundColor)
            .padding(
                vertical = verticalPadding,
                horizontal = horizontalPadding
            ),
        contentAlignment = contentAlignment
    ) {
        chipContent()
    }
}
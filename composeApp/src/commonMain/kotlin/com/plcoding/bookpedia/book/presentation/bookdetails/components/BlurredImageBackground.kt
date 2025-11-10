package com.plcoding.bookpedia.book.presentation.bookdetails.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import coil3.compose.rememberAsyncImagePainter

@Composable
fun BlurredImageBackground(
    modifier: Modifier,
    imageUrl: String?,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onBackClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        onSuccess = {
            val size = it.painter.intrinsicSize
            imageLoadResult = if (size.width > 1 && size.width > 1) {
                Result.success(it.painter)
            } else {
                Result.failure(Exception("Invalid image dimension"))
            }
        }
    )

}
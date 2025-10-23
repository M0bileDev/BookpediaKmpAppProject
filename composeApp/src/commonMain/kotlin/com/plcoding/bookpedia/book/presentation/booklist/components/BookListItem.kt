package com.plcoding.bookpedia.book.presentation.booklist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.average_rating_x
import cmp_bookpedia.composeapp.generated.resources.book_cover_hint
import cmp_bookpedia.composeapp.generated.resources.book_error_2
import cmp_bookpedia.composeapp.generated.resources.chevron_right_24px
import cmp_bookpedia.composeapp.generated.resources.details_icon_hint
import cmp_bookpedia.composeapp.generated.resources.star_24px
import cmp_bookpedia.composeapp.generated.resources.star_hint
import coil3.compose.rememberAsyncImagePainter
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.presentation.LightBlue
import com.plcoding.bookpedia.core.presentation.SandYellow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

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
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp).height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                var imageResult by remember { mutableStateOf<Result<Painter>?>(null) }
                val painter = rememberAsyncImagePainter(
                    model = book.imageUrl,
                    onSuccess = { state ->
                        imageResult =
                            if (state.painter.intrinsicSize.height > 1 && state.painter.intrinsicSize.width > 1) {
                                Result.success(state.painter)
                            } else {
                                Result.failure(Exception("Painter size is incorrect"))
                            }
                    },
                    onError = { error ->
                        error.result.throwable.printStackTrace()
                        imageResult = Result.failure(error.result.throwable)
                    }
                )

                when (val result = imageResult) {
                    null -> CircularProgressIndicator()
                    else -> {
                        Image(
                            modifier = Modifier.aspectRatio(
                                0.65f,
                                matchHeightConstraintsFirst = true
                            ),
                            painter = if (result.isSuccess) painter else painterResource(Res.drawable.book_error_2),
                            contentDescription = stringResource(Res.string.book_cover_hint),
                            contentScale = if (result.isSuccess) ContentScale.Crop else ContentScale.Fit,
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxHeight().padding(horizontal = 16.dp).weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                book.authors.firstOrNull()?.let { author ->
                    Text(
                        text = author,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                book.averageRating?.let { rating ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(Res.string.average_rating_x, rating),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Icon(
                            painter = painterResource(Res.drawable.star_24px),
                            contentDescription = stringResource(Res.string.star_hint),
                            tint = SandYellow
                        )
                    }
                }

            }
            Icon(
                modifier = Modifier.size(36.dp),
                painter = painterResource(Res.drawable.chevron_right_24px),
                contentDescription = stringResource(Res.string.details_icon_hint)
            )
        }
    }
}

@Preview
@Composable
fun PreviewBookListItem() {
    MaterialTheme {
        BookListItem(
            book = Book(
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
            onClick = {}
        )
    }
}
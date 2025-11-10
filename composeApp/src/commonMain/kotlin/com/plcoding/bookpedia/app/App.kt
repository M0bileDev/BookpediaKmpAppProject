package com.plcoding.bookpedia.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.plcoding.bookpedia.book.presentation.BookSharedViewModel
import com.plcoding.bookpedia.book.presentation.booklist.BookListScreen
import com.plcoding.bookpedia.book.presentation.booklist.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.Book.BookGraph,
        ) {

            navigation<Route.Book.BookGraph>(
                startDestination = Route.Book.BookList
            ) {
                composable<Route.Book.BookList> { navBackStackEntry ->
                    val viewModel = koinViewModel<BookListViewModel>()
                    val sharedViewModel =
                        navBackStackEntry.sharedKoinViewModel<BookSharedViewModel>(navController)

                    BookListScreen(
                        viewModel = viewModel,
                        navigateToBookDetails = { book ->
                            sharedViewModel.onSelectedBook(book)
                            navController.navigate(
                                route = Route.Book.BookDetails
                            )
                        }
                    )
                }
                composable<Route.Book.BookDetails> { navBackStackEntry ->
                    val sharedViewModel =
                        navBackStackEntry.sharedKoinViewModel<BookSharedViewModel>(navController)
                    val book by sharedViewModel.selectedBook.collectAsStateWithLifecycle()
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Text("Book details screen with book: $book")
                    }
                }
            }
        }
    }
}



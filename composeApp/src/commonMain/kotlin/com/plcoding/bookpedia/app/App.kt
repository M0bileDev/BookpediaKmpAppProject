package com.plcoding.bookpedia.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.plcoding.bookpedia.book.presentation.BookSharedViewModel
import com.plcoding.bookpedia.book.presentation.bookdetails.BookDetailsAction
import com.plcoding.bookpedia.book.presentation.bookdetails.BookDetailsScreen
import com.plcoding.bookpedia.book.presentation.bookdetails.BookDetailsViewModel
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
                    val viewModel = koinViewModel<BookDetailsViewModel>()

                    LaunchedEffect(book) {
                        book?.let {
                            viewModel.onAction(BookDetailsAction.OnSelectedBookChanged(it))
                        }
                    }

                    BookDetailsScreen(
                        viewModel = viewModel,
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
    }
}



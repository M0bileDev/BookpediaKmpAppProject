package com.plcoding.bookpedia.book.presentation.bookdetails

sealed interface BookDetailsViewModelAction {
    data object OnNavigateBack : BookDetailsViewModelAction
}
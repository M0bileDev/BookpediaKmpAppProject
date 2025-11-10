package com.plcoding.bookpedia.app

import kotlinx.serialization.Serializable

sealed interface Route {

    sealed interface Book : Route {
        @Serializable
        data object BookGraph : Book

        @Serializable
        data object BookList : Book

        @Serializable
        data object BookDetails : Book
    }


}
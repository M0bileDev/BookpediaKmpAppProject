package com.plcoding.bookpedia.book.data.network

import com.plcoding.bookpedia.book.data.dto.search.SearchedBookDto
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        limit: Int? = null
    ): Result<SearchedBookDto, DataError>
}
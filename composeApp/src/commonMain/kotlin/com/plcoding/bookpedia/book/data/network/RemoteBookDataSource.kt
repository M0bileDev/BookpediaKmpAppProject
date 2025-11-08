package com.plcoding.bookpedia.book.data.network

import com.plcoding.bookpedia.book.data.dto.search.SearchedBookResponseDto
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchedBookResponseDto, DataError.Remote>
}
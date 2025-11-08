package com.plcoding.bookpedia.book.data.repository

import com.plcoding.bookpedia.book.data.mapper.toBook
import com.plcoding.bookpedia.book.data.network.RemoteBookDataSource
import com.plcoding.bookpedia.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
) {
    suspend fun searchBooks(
        query: String
    ) {
        remoteBookDataSource.searchBooks(query)
            .map { dto -> dto.results.map { searchedBookDto -> searchedBookDto.toBook() } }
    }
}
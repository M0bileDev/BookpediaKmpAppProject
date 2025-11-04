package com.plcoding.bookpedia.book.data.dto.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchedBookResponseDto(
    @SerialName("docs") val results: List<SearchedBookDto>
)
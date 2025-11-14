package com.plcoding.bookpedia.book.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class BookWorkDto(
    val description: String? = null
)
package com.muammarahlnn.myshelf.backend.dto.response

import com.muammarahlnn.myshelf.backend.entity.Book

/**
 * @Author Muammar Ahlan Abimanyu
 * @File BookDetailsResponse.kt, 26/08/2024 23.06
 */
data class BookDetailsResponse(
    val id: String,
    val title: String,
    val desc: String? = null,
    val authors: List<AuthorResponse> = emptyList(),
    val categories: List<CategoryResponse> = emptyList(),
    val publisher: PublisherResponse? = null,
)

fun Book.toDetailsResponse(): BookDetailsResponse = BookDetailsResponse(
    id = id,
    title = title,
    desc = desc,
    authors = authors.sortedBy { it.id }.map { it.toResponse() },
    categories = categories.sortedBy { it.name }.map { it.toResponse() },
    publisher = publisher?.toResponse(),
)
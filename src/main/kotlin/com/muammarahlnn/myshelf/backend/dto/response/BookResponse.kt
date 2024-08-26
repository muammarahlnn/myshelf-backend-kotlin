package com.muammarahlnn.myshelf.backend.dto.response

import com.muammarahlnn.myshelf.backend.entity.Book

data class BookResponse(
    val id: String,
    val title: String,
    val desc: String? = null,
    val authors: List<AuthorResponse> = emptyList(),
    val categories: List<CategoryResponse> = emptyList(),
    val publisher: PublisherResponse? = null,
)

fun Book.toResponse(): BookResponse = BookResponse(
    id = id,
    title = title,
    desc = desc,
    authors = authors.sortedBy { it.id }.map { it.toResponse() },
    categories = categories.sortedBy { it.name }.map { it.toResponse() },
    publisher = publisher?.toResponse(),
)
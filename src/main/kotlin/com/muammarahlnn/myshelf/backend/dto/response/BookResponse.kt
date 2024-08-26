package com.muammarahlnn.myshelf.backend.dto.response

import com.muammarahlnn.myshelf.backend.entity.Book

data class BookResponse(
    val id: String,
    val title: String,
    val desc: String? = null,
    val publisher: PublisherResponse? = null,
    val authors: List<AuthorResponse>? = null,
)

fun Book.toResponse(): BookResponse = BookResponse(
    id = id,
    title = title,
    desc = desc,
    publisher = publisher?.toResponse(),
    authors = authors.sortedBy { it.id }.map { it.toResponse() }
)
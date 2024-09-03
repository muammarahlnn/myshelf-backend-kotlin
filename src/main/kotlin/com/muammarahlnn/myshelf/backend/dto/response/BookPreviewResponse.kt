package com.muammarahlnn.myshelf.backend.dto.response

import com.muammarahlnn.myshelf.backend.entity.Book

data class BookPreviewResponse(
    val id: String,
    val title: String?,
    val authors: List<AuthorResponse> = emptyList(),
)

fun Book.toPreviewResponse(): BookPreviewResponse = BookPreviewResponse(
    id = id,
    title = title,
    authors = authors.sortedBy { it.id }.map { it.toResponse() },
)
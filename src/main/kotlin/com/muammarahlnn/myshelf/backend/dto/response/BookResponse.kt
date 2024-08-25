package com.muammarahlnn.myshelf.backend.dto.response

import com.muammarahlnn.myshelf.backend.entity.Book

data class BookResponse(
    val id: String,
    val title: String,
    val desc: String? = null,
)

fun Book.toResponse(): BookResponse = BookResponse(
    id = id,
    title = title,
    desc = desc,
)
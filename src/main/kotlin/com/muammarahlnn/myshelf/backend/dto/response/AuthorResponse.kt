package com.muammarahlnn.myshelf.backend.dto.response

import com.muammarahlnn.myshelf.backend.entity.Author
import com.muammarahlnn.myshelf.backend.exception.InternalServerException

/**
 * @Author Muammar Ahlan Abimanyu
 * @File AuthorResponse.kt, 25/08/2024 21.53
 */
data class AuthorResponse(
    val id: Long,
    val name: String?,
)

fun Author.toResponse(): AuthorResponse = AuthorResponse(
    id = id ?: throw InternalServerException("Author entity id is null"),
    name = name,
)
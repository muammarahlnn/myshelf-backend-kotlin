package com.muammarahlnn.myshelf.backend.dto.response

import com.muammarahlnn.myshelf.backend.entity.Publisher
import com.muammarahlnn.myshelf.backend.exception.InternalServerException

/**
 * @Author Muammar Ahlan Abimanyu
 * @File PublisherResponse.kt, 26/08/2024 00.48
 */
data class PublisherResponse(
    val id: Long,
    val name: String,
)

fun Publisher.toResponse(): PublisherResponse = PublisherResponse(
    id = id ?: throw InternalServerException("Publisher entity id is null"),
    name = name,
)
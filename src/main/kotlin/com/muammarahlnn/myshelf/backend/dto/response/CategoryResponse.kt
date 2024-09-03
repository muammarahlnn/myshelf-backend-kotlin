package com.muammarahlnn.myshelf.backend.dto.response

import com.muammarahlnn.myshelf.backend.entity.Category
import com.muammarahlnn.myshelf.backend.exception.InternalServerException

/**
 * @Author Muammar Ahlan Abimanyu
 * @File CategoryResponse.kt, 25/08/2024 22.50
 */
data class CategoryResponse(
    val id: Long,
    val name: String?,
)

fun Category.toResponse(): CategoryResponse = CategoryResponse(
    id = id ?: throw InternalServerException("Category entity id is null"),
    name = name,
)
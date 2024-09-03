package com.muammarahlnn.myshelf.backend.dto.request

import jakarta.validation.constraints.Min

/**
 * @Author Muammar Ahlan Abimanyu
 * @File GetBooksRequest.kt, 25/08/2024 15.32
 */
data class PagingRequest(
    @field:Min(0)
    val page: Int?,

    @field:Min(1)
    val size: Int?,
)
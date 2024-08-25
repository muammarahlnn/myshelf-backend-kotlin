package com.muammarahlnn.myshelf.backend.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * @Author Muammar Ahlan Abimanyu
 * @File CreateAuthorRequest.kt, 25/08/2024 21.43
 */
data class CreateAuthorRequest(
    @field:NotNull
    @field:NotBlank
    val name: String,
)
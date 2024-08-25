package com.muammarahlnn.myshelf.backend.dto.request

import jakarta.validation.constraints.NotBlank

/**
 * @Author Muammar Ahlan Abimanyu
 * @File UpdateAuthorRequest.kt, 25/08/2024 21.45
 */
data class UpdateAuthorRequest(
    @field:NotBlank val name: String,
)
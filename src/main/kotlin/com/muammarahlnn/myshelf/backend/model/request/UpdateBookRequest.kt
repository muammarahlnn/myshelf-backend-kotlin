package com.muammarahlnn.myshelf.backend.model.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * @Author Muammar Ahlan Abimanyu
 * @File UpdateBookRequest.kt, 25/08/2024 16.57
 */
data class UpdateBookRequest(
    @field:NotBlank val title: String,
    @field:NotBlank val desc: String?,
)
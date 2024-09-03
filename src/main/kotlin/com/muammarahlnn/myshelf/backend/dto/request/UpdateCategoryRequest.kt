package com.muammarahlnn.myshelf.backend.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * @Author Muammar Ahlan Abimanyu
 * @File UpdateCategoryRequest.kt, 25/08/2024 22.52
 */
data class UpdateCategoryRequest(
    @field:NotNull @field:NotBlank val name: String?,
)
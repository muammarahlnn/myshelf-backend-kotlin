package com.muammarahlnn.myshelf.backend.dto.request

import jakarta.validation.constraints.NotBlank

/**
 * @Author Muammar Ahlan Abimanyu
 * @File UpdateCategoryRequest.kt, 25/08/2024 22.52
 */
data class UpdateCategoryRequest(
    @field:NotBlank val name: String,
)
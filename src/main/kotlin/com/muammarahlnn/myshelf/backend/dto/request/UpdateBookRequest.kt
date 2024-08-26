package com.muammarahlnn.myshelf.backend.dto.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

/**
 * @Author Muammar Ahlan Abimanyu
 * @File UpdateBookRequest.kt, 25/08/2024 16.57
 */
data class UpdateBookRequest(
    @field:NotBlank val title: String,
    @field:NotBlank val desc: String?,
    @field:Min(1) val publisherId: Long?,
    @field:NotEmpty val authorIds: Set<Long>?,
)
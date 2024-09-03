package com.muammarahlnn.myshelf.backend.dto.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class CreateBookRequest(
    @field:NotNull @field:NotBlank val title: String,
    val desc: String? = null,
    @field:NotNull @field:NotEmpty val authorIds: List<Long>?,
    @field:NotNull @field:NotEmpty val categoryIds: List<Long>?,
    @field:NotNull @field:Min(1) val publisherId: Long?,
)
package com.muammarahlnn.myshelf.backend.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * @Author Muammar Ahlan Abimanyu
 * @File CreatePublisherRequest.kt, 26/08/2024 00.55
 */
data class CreatePublisherRequest(
    @field:NotNull
    @field:NotBlank
    val name: String,
)
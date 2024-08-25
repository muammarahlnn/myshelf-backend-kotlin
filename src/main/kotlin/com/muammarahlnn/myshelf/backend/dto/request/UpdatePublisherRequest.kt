package com.muammarahlnn.myshelf.backend.dto.request

import jakarta.validation.constraints.NotBlank

/**
 * @Author Muammar Ahlan Abimanyu
 * @File UpdatePublisherRequest.kt, 26/08/2024 00.55
 */
data class UpdatePublisherRequest(
    @field:NotBlank val name: String,
)
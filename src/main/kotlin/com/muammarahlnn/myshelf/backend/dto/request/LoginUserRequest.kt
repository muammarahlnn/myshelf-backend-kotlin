package com.muammarahlnn.myshelf.backend.dto.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

/**
 * @Author Muammar Ahlan Abimanyu
 * @File LoginRequest.kt, 28/08/2024 23.12
 */
data class LoginUserRequest(
    @field:NotEmpty(message = "Username is required")
    @field:Size(min = 2, max = 100, message = "Username length must be between 2 and 100 characters")
    val username: String,

    @field:NotEmpty(message = "Password is required")
    @field:Size(min = 6, max = 100, message = "Password length must be between 6 and 100 characters")
    val password: String,
)
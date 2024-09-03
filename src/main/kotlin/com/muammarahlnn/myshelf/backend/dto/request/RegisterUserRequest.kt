package com.muammarahlnn.myshelf.backend.dto.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

/**
 * @Author Muammar Ahlan Abimanyu
 * @File RegisterRequest.kt, 28/08/2024 23.10
 */
data class RegisterUserRequest(
    @field:NotNull(message = "Username is required")
    @field:NotEmpty(message = "Username must not be empty")
    @field:Size(min = 2, max = 100, message = "Username length must be between 2 and 100 characters")
    val username: String?,

    @field:NotNull(message = "Full name is required")
    @field:NotEmpty(message = "Full name must not be empty")
    val fullName: String?,

    @field:NotNull(message = "Password is required")
    @field:NotEmpty(message = "Password must not be empty")
    @field:Size(min = 6, max = 100, message = "Password length must be between 6 and 100 characters")
    val password: String?,
)
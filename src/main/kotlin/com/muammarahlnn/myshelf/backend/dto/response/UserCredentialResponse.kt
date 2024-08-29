package com.muammarahlnn.myshelf.backend.dto.response

import com.muammarahlnn.myshelf.backend.entity.User

/**
 * @Author Muammar Ahlan Abimanyu
 * @File UserCredentialResponse.kt, 29/08/2024 19.52
 */
data class UserCredentialResponse(
    val username: String,
    val fullName: String,
)

fun User.toCredentialResponse(): UserCredentialResponse = UserCredentialResponse(
    username = username,
    fullName = fullName,
)
package com.muammarahlnn.myshelf.backend.service

import org.springframework.security.core.userdetails.UserDetails

/**
 * @Author Muammar Ahlan Abimanyu
 * @File JwtService.kt, 28/08/2024 21.57
 */
interface JwtService {

    fun extractUsername(token: String): String

    fun generateToken(userDetails: UserDetails): String

    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean
}
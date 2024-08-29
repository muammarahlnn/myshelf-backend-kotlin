package com.muammarahlnn.myshelf.backend.service

import com.muammarahlnn.myshelf.backend.dto.request.LoginUserRequest
import com.muammarahlnn.myshelf.backend.dto.request.RegisterUserRequest
import com.muammarahlnn.myshelf.backend.dto.response.AuthenticationResponse

/**
 * @Author Muammar Ahlan Abimanyu
 * @File AuthenticationService.kt, 28/08/2024 23.14
 */
interface AuthenticationService {

    fun signup(request: RegisterUserRequest): AuthenticationResponse

    fun login(request: LoginUserRequest): AuthenticationResponse
}
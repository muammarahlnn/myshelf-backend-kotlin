package com.muammarahlnn.myshelf.backend.controller

import com.muammarahlnn.myshelf.backend.dto.request.LoginUserRequest
import com.muammarahlnn.myshelf.backend.dto.request.RegisterUserRequest
import com.muammarahlnn.myshelf.backend.dto.response.AuthenticationResponse
import com.muammarahlnn.myshelf.backend.dto.response.base.WebResponse
import com.muammarahlnn.myshelf.backend.service.AuthenticationService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @Author Muammar Ahlan Abimanyu
 * @File AuthenticationController.kt, 28/08/2024 23.25
 */
@RestController
@RequestMapping("auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService,
) {

    @PostMapping("signup")
    fun signup(@RequestBody @Valid requestBody: RegisterUserRequest): WebResponse<AuthenticationResponse> =
        WebResponse.success(authenticationService.signup(requestBody))

    @PostMapping("login")
    fun login(@RequestBody requestBody: LoginUserRequest): WebResponse<AuthenticationResponse> =
        WebResponse.success(authenticationService.login(requestBody))
}
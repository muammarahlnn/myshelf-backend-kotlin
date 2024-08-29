package com.muammarahlnn.myshelf.backend.service.impl

import com.muammarahlnn.myshelf.backend.dto.request.LoginUserRequest
import com.muammarahlnn.myshelf.backend.dto.request.RegisterUserRequest
import com.muammarahlnn.myshelf.backend.dto.response.AuthenticationResponse
import com.muammarahlnn.myshelf.backend.dto.response.UserCredentialResponse
import com.muammarahlnn.myshelf.backend.entity.User
import com.muammarahlnn.myshelf.backend.exception.NotFoundException
import com.muammarahlnn.myshelf.backend.repository.UserRepository
import com.muammarahlnn.myshelf.backend.service.AuthenticationService
import com.muammarahlnn.myshelf.backend.service.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * @Author Muammar Ahlan Abimanyu
 * @File AuthenticationServiceImpl.kt, 28/08/2024 23.15
 */
@Service
class AuthenticationServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
) : AuthenticationService {

    override fun signup(request: RegisterUserRequest): AuthenticationResponse {
        val user = User(
            username = request.username,
            fullName = request.fullName,
            password = passwordEncoder.encode(request.password),
        )
        userRepository.save(user)

        val token = jwtService.generateToken(user)
        return AuthenticationResponse(token = token)
    }

    override fun login(request: LoginUserRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.username,
                request.password
            )
        )

        val user = userRepository.findByUsername(request.username) ?: throw NotFoundException("User not found")
        val token = jwtService.generateToken(user)
        return AuthenticationResponse(token = token)
    }
}
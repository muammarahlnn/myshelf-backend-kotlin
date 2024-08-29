package com.muammarahlnn.myshelf.backend.config

import com.muammarahlnn.myshelf.backend.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 * @Author Muammar Ahlan Abimanyu
 * @File JwtAuthenticationFilter.kt, 28/08/2024 22.41
 */
@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader(AUTHORIZATION_HEADER_NAME)
        logger.debug(authHeader)
        if (authHeader == null || !authHeader.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
            logger.info("No valid Authorization header found. Skipping JWT validation.")
            filterChain.doFilter(request, response)
            return
        }

        val jwt = authHeader.substringAfter(AUTHORIZATION_HEADER_PREFIX)
        val userUsername = try {
            jwtService.extractUsername(jwt)
        } catch (ex: Exception) {
            logger.warn("Failed to extract username from JWT: ${ex.message}")
            filterChain.doFilter(request, response)
            return
        }

        if (SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService.loadUserByUsername(userUsername)
            if (jwtService.isTokenValid(jwt, userDetails)) {
                val authToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                ).apply {
                    details = WebAuthenticationDetailsSource().buildDetails(request)
                }

                SecurityContextHolder.getContext().authentication = authToken
            }
        }
        filterChain.doFilter(request, response)
    }

    private companion object {

        const val AUTHORIZATION_HEADER_NAME = "Authorization"

        const val AUTHORIZATION_HEADER_PREFIX = "Bearer "
    }
}
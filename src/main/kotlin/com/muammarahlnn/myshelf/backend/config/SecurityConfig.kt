package com.muammarahlnn.myshelf.backend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * @Author Muammar Ahlan Abimanyu
 * @File SecurityConfig.kt, 28/08/2024 23.05
 */
@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationProvider: AuthenticationProvider,
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http.run {
        csrf { it.disable() }
        authorizeHttpRequests { authorize ->
            authorize.apply {
                requestMatchers(HttpMethod.GET, "ping").permitAll()
                requestMatchers("auth/**").permitAll()
                anyRequest().authenticated()
            }
        }
        sessionManagement { session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
        authenticationProvider(authenticationProvider)
        addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        build()
    }
}
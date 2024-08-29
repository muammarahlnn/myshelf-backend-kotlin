package com.muammarahlnn.myshelf.backend.service.impl

import com.muammarahlnn.myshelf.backend.service.JwtService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*

/**
 * @Author Muammar Ahlan Abimanyu
 * @File JwtServiceImpl.kt, 28/08/2024 22.02
 */
@Service
class JwtServiceImpl : JwtService {

    @Value("\${security.jwt.secret-key}")
    private lateinit var secretKey: String

    @Value("\${security.jwt.expiration-time}")
    private var tokenExpiration: Long = 0L

    private val signInKey: Key
        get() {
            val keyBytes = Decoders.BASE64.decode(secretKey)
            return Keys.hmacShaKeyFor(keyBytes)
        }

    override fun extractUsername(token: String): String = extractClaim(token, Claims::getSubject)

    private fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String): Claims =
        Jwts
            .parserBuilder()
            .setSigningKey(signInKey)
            .build()
            .parseClaimsJws(token)
            .body

    override fun generateToken(userDetails: UserDetails): String = buildToken(
        extraClaims = emptyMap(),
        userDetails = userDetails,
    )

    override fun generateToken(
        extraClaims: Map<String, Any>,
        userDetails: UserDetails)
    : String = buildToken(
        extraClaims = extraClaims,
        userDetails = userDetails,
    )

    private fun buildToken(
        extraClaims: Map<String, Any>,
        userDetails: UserDetails,
        tokenExpiration: Long = this.tokenExpiration,
    ): String = Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.username)
        .setIssuedAt(Date(System.currentTimeMillis()))
        .setExpiration(Date(System.currentTimeMillis() + tokenExpiration))
        .signWith(signInKey)
        .compact()

    override fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean = extractExpiration(token).before(Date())

    private fun extractExpiration(token: String): Date = extractClaim(token, Claims::getExpiration)
}
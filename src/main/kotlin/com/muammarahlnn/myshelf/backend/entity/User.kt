package com.muammarahlnn.myshelf.backend.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

/**
 * @Author Muammar Ahlan Abimanyu
 * @File User.kt, 28/08/2024 20.35
 */
@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue
    @Column(nullable = false)
    val id: Int? = null,

    @get:JvmName(name = "_getUsername")
    @Column(nullable = false)
    var username: String?,

    @Column(name = "full_name", nullable = false)
    var fullName: String?,

    @get:JvmName(name = "_getPassword")
    @Column(nullable = false)
    var password: String?,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getPassword(): String? = password

    override fun getUsername(): String? = username
}
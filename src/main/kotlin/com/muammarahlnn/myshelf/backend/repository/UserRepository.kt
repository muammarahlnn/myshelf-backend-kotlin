package com.muammarahlnn.myshelf.backend.repository

import com.muammarahlnn.myshelf.backend.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @Author Muammar Ahlan Abimanyu
 * @File UserRepository.kt, 28/08/2024 21.36
 */
@Repository
interface UserRepository : JpaRepository<User, Int> {

    fun findByUsername(username: String): User?
}
package com.muammarahlnn.myshelf.backend.repository

import com.muammarahlnn.myshelf.backend.entity.Author
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @Author Muammar Ahlan Abimanyu
 * @File AuthorRepository.kt, 25/08/2024 22.02
 */
@Repository
interface AuthorRepository : JpaRepository<Author, Long> {

    fun findByUserId(
        userId: Int,
        pageable: Pageable,
    ): Page<Author>
}
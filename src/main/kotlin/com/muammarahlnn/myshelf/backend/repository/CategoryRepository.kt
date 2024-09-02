package com.muammarahlnn.myshelf.backend.repository

import com.muammarahlnn.myshelf.backend.entity.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @Author Muammar Ahlan Abimanyu
 * @File CategoryRepository.kt, 25/08/2024 22.48
 */
@Repository
interface CategoryRepository : JpaRepository<Category, Long> {

    fun findByUserId(
        userId: Int,
        pageable: Pageable,
    ): Page<Category>
}
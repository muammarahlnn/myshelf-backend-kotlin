package com.muammarahlnn.myshelf.backend.repository

import com.muammarahlnn.myshelf.backend.entity.Publisher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @Author Muammar Ahlan Abimanyu
 * @File PublisherRepository.kt, 26/08/2024 00.58
 */
@Repository
interface PublisherRepository : JpaRepository<Publisher, Long> {

    fun findByUserId(
        userId: Int,
        pageable: Pageable,
    ): Page<Publisher>
}
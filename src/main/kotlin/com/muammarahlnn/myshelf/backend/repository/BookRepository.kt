package com.muammarahlnn.myshelf.backend.repository

import com.muammarahlnn.myshelf.backend.entity.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, String> {

    fun findByUserId(
        userId: Int,
        pageable: Pageable,
    ): Page<Book>
}
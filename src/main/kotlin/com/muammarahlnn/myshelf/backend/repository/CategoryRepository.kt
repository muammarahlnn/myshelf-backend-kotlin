package com.muammarahlnn.myshelf.backend.repository

import com.muammarahlnn.myshelf.backend.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @Author Muammar Ahlan Abimanyu
 * @File CategoryRepository.kt, 25/08/2024 22.48
 */
@Repository
interface CategoryRepository : JpaRepository<Category, Long>
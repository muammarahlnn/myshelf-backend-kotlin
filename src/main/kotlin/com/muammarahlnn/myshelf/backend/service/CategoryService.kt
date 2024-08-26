package com.muammarahlnn.myshelf.backend.service

import com.muammarahlnn.myshelf.backend.dto.request.CreateCategoryRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdateCategoryRequest
import com.muammarahlnn.myshelf.backend.dto.response.BookPreviewResponse
import com.muammarahlnn.myshelf.backend.dto.response.CategoryResponse

/**
 * @Author Muammar Ahlan Abimanyu
 * @File CategoryService.kt, 25/08/2024 22.48
 */
interface CategoryService {

    fun createCategory(request: CreateCategoryRequest): CategoryResponse

    fun getCategories(request: PagingRequest): List<CategoryResponse>

    fun getCategory(categoryId: Long): CategoryResponse

    fun updateCategory(categoryId: Long, request: UpdateCategoryRequest): CategoryResponse

    fun deleteCategory(categoryId: Long)

    fun getCategoryBooks(categoryId: Long): List<BookPreviewResponse>
}
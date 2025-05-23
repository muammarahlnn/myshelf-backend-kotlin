package com.muammarahlnn.myshelf.backend.service.impl

import com.muammarahlnn.myshelf.backend.controller.provider.PagingProvider
import com.muammarahlnn.myshelf.backend.dto.request.CreateCategoryRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdateCategoryRequest
import com.muammarahlnn.myshelf.backend.dto.response.BookPreviewResponse
import com.muammarahlnn.myshelf.backend.dto.response.CategoryResponse
import com.muammarahlnn.myshelf.backend.dto.response.toPreviewResponse
import com.muammarahlnn.myshelf.backend.dto.response.toResponse
import com.muammarahlnn.myshelf.backend.entity.Category
import com.muammarahlnn.myshelf.backend.exception.NotFoundException
import com.muammarahlnn.myshelf.backend.repository.CategoryRepository
import com.muammarahlnn.myshelf.backend.service.CategoryService
import com.muammarahlnn.myshelf.backend.service.UserService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 * @Author Muammar Ahlan Abimanyu
 * @File CategoryServiceImpl.kt, 25/08/2024 22.57
 */
@Service
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository,
    private val userService: UserService,
) : CategoryService {

    override fun createCategory(request: CreateCategoryRequest): CategoryResponse {
        val category = Category(
            name = request.name,
            user = userService.getCurrentUser(),
        )
        return categoryRepository.save(category).toResponse()
    }

    override fun getCategories(request: PagingRequest): List<CategoryResponse> {
        val userId = userService.getCurrentUser().id ?: return emptyList()
        return categoryRepository.findByUserId(
            userId = userId,
            pageable = PageRequest.of(
                request.page ?: PagingProvider.DEFAULT_PAGE,
                request.size ?: PagingProvider.DEFAULT_SIZE,
                Sort.by(Sort.Direction.ASC, Category::name.name)
            ),
        ).content.map { it.toResponse() }
    }


    override fun getCategory(categoryId: Long): CategoryResponse =
        findCategoryByIdOrThrowNotFound(categoryId).toResponse()

    override fun updateCategory(categoryId: Long, request: UpdateCategoryRequest): CategoryResponse {
        val category = findCategoryByIdOrThrowNotFound(categoryId)
        category.apply {
            name = request.name
        }

        return categoryRepository.save(category).toResponse()
    }

    override fun deleteCategory(categoryId: Long) {
        val category = findCategoryByIdOrThrowNotFound(categoryId)
        categoryRepository.delete(category)
    }

    override fun getCategoryBooks(categoryId: Long): List<BookPreviewResponse> {
        val category = findCategoryByIdOrThrowNotFound(categoryId)
        return category.books.map { it.toPreviewResponse() }
    }

    private fun findCategoryByIdOrThrowNotFound(categoryId: Long): Category =
        categoryRepository.findByIdOrNull(categoryId)
            ?: throw NotFoundException("Category with id $categoryId not found")
}
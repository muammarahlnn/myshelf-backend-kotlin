package com.muammarahlnn.myshelf.backend.controller

import com.muammarahlnn.myshelf.backend.controller.provider.PagingProvider
import com.muammarahlnn.myshelf.backend.dto.request.CreateCategoryRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdateCategoryRequest
import com.muammarahlnn.myshelf.backend.dto.response.BookPreviewResponse
import com.muammarahlnn.myshelf.backend.dto.response.CategoryResponse
import com.muammarahlnn.myshelf.backend.dto.response.base.WebResponse
import com.muammarahlnn.myshelf.backend.service.CategoryService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

/**
 * @Author Muammar Ahlan Abimanyu
 * @File CategoryController.kt, 25/08/2024 23.02
 */
@RestController
@RequestMapping("categories")
class CategoryController(
    private val categoryService: CategoryService,
) : PagingProvider<CategoryResponse>() {

    override fun getPagedData(pagingRequest: PagingRequest): List<CategoryResponse> =
        categoryService.getCategories(pagingRequest)

    @PostMapping
    fun createCategory(@RequestBody @Valid requestBody: CreateCategoryRequest): WebResponse<CategoryResponse> =
        WebResponse.success(categoryService.createCategory(requestBody))

    @GetMapping("{categoryId}")
    fun getCategory(@PathVariable categoryId: Long): WebResponse<CategoryResponse> =
        WebResponse.success(categoryService.getCategory(categoryId))

    @PutMapping("{categoryId}")
    fun updateCategory(
        @PathVariable categoryId: Long,
        @RequestBody @Valid requestBody: UpdateCategoryRequest,
    ): WebResponse<CategoryResponse> = WebResponse.success(
        categoryService.updateCategory(
            categoryId = categoryId,
            request = requestBody,
        )
    )

    @DeleteMapping("{categoryId}")
    fun deleteCategory(@PathVariable categoryId: Long): WebResponse<String> {
        categoryService.deleteCategory(categoryId)
        return WebResponse.success("Category with id $categoryId successfully deleted")
    }

    @GetMapping("{categoryId}/books")
    fun getCategoryBooks(@PathVariable categoryId: Long): WebResponse<List<BookPreviewResponse>> =
        WebResponse.success(categoryService.getCategoryBooks(categoryId))
}
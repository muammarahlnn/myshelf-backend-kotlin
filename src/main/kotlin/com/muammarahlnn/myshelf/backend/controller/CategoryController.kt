package com.muammarahlnn.myshelf.backend.controller

import com.muammarahlnn.myshelf.backend.controller.provider.PagingProvider
import com.muammarahlnn.myshelf.backend.dto.request.CreateCategoryRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdateCategoryRequest
import com.muammarahlnn.myshelf.backend.dto.response.CategoryResponse
import com.muammarahlnn.myshelf.backend.dto.response.base.WebResponse
import com.muammarahlnn.myshelf.backend.service.CategoryService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    fun createCategory(@RequestBody requestBody: CreateCategoryRequest): WebResponse<CategoryResponse> =
        WebResponse.success(categoryService.createCategory(requestBody))

    @GetMapping("{categoryId}")
    fun getCategory(@PathVariable categoryId: Long): WebResponse<CategoryResponse> =
        WebResponse.success(categoryService.getCategory(categoryId))

    @PutMapping("{categoryId}")
    fun updateCategory(
        @PathVariable categoryId: Long,
        @RequestBody requestBody: UpdateCategoryRequest,
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
}
package com.muammarahlnn.myshelf.backend.controller.provider

import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.response.base.WebResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @Author Muammar Ahlan Abimanyu
 * @File PagingProvider.kt, 25/08/2024 22.13
 */
@RestController
abstract class PagingProvider <T> {

    protected abstract fun getPagedData(pagingRequest: PagingRequest): List<T>

    @GetMapping
    final fun getList(
        @RequestParam(value = "page", defaultValue = "$DEFAULT_PAGE") page: Int,
        @RequestParam(value = "size", defaultValue = "$DEFAULT_SIZE") size: Int,
    ): WebResponse<List<T>> = WebResponse.success(
        getPagedData(
            PagingRequest(
                page = page,
                size = size,
            )
        )
    )

    companion object {

        const val DEFAULT_PAGE = 0

        const val DEFAULT_SIZE = 10
    }
}
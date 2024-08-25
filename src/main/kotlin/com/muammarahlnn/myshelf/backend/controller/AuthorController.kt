package com.muammarahlnn.myshelf.backend.controller

import com.muammarahlnn.myshelf.backend.controller.provider.PagingProvider
import com.muammarahlnn.myshelf.backend.dto.request.CreateAuthorRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdateAuthorRequest
import com.muammarahlnn.myshelf.backend.dto.response.AuthorResponse
import com.muammarahlnn.myshelf.backend.dto.response.base.WebResponse
import com.muammarahlnn.myshelf.backend.service.AuthorService
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
 * @File AuthorController.kt, 25/08/2024 22.09
 */
@RestController
@RequestMapping("authors")
class AuthorController(
    private val authorService: AuthorService,
) : PagingProvider<AuthorResponse>() {

    override fun getPagedData(pagingRequest: PagingRequest): List<AuthorResponse> =
        authorService.getAuthors(pagingRequest)

    @PostMapping
    fun createAuthor(@RequestBody requestBody: CreateAuthorRequest): WebResponse<AuthorResponse> =
        WebResponse.success(authorService.createAuthor(requestBody))

    @GetMapping("{authorId}")
    fun getAuthor(@PathVariable authorId: Long): WebResponse<AuthorResponse> =
        WebResponse.success(authorService.getAuthor(authorId))

    @PutMapping("{authorId}")
    fun updateAuthor(
        @PathVariable authorId: Long,
        @RequestBody requestBody: UpdateAuthorRequest,
    ): WebResponse<AuthorResponse> = WebResponse.success(
        authorService.updateAuthor(
            authorId = authorId,
            request = requestBody,
        )
    )

    @DeleteMapping("{authorId}")
    fun deleteAuthor(@PathVariable authorId: Long): WebResponse<String> {
        authorService.deleteAuthor(authorId)
        return WebResponse.success("Author with id $authorId successfully deleted")
    }
}
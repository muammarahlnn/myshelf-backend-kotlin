package com.muammarahlnn.myshelf.backend.service

import com.muammarahlnn.myshelf.backend.dto.request.CreateAuthorRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdateAuthorRequest
import com.muammarahlnn.myshelf.backend.dto.response.AuthorResponse
import com.muammarahlnn.myshelf.backend.dto.response.BookPreviewResponse

/**
 * @Author Muammar Ahlan Abimanyu
 * @File AuthorService.kt, 25/08/2024 21.37
 */
interface AuthorService {

    fun createAuthor(request: CreateAuthorRequest): AuthorResponse

    fun getAuthors(request: PagingRequest): List<AuthorResponse>

    fun getAuthor(authorId: Long): AuthorResponse

    fun updateAuthor(authorId: Long, request: UpdateAuthorRequest): AuthorResponse

    fun deleteAuthor(authorId: Long)

    fun getAuthorBooks(authorId: Long): List<BookPreviewResponse>
}
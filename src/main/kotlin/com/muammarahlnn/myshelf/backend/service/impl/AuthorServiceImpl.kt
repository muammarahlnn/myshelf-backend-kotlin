package com.muammarahlnn.myshelf.backend.service.impl

import com.muammarahlnn.myshelf.backend.dto.request.CreateAuthorRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdateAuthorRequest
import com.muammarahlnn.myshelf.backend.dto.response.AuthorResponse
import com.muammarahlnn.myshelf.backend.dto.response.BookPreviewResponse
import com.muammarahlnn.myshelf.backend.dto.response.toPreviewResponse
import com.muammarahlnn.myshelf.backend.dto.response.toResponse
import com.muammarahlnn.myshelf.backend.entity.Author
import com.muammarahlnn.myshelf.backend.exception.NotFoundException
import com.muammarahlnn.myshelf.backend.repository.AuthorRepository
import com.muammarahlnn.myshelf.backend.service.AuthorService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 * @Author Muammar Ahlan Abimanyu
 * @File AuthorServiceImpl.kt, 25/08/2024 22.04
 */
@Service
class AuthorServiceImpl(
    private val authorRepository: AuthorRepository,
) : AuthorService {

    override fun createAuthor(request: CreateAuthorRequest): AuthorResponse {
        val author = Author(name = request.name)
        return authorRepository.save(author).toResponse()
    }

    override fun getAuthors(request: PagingRequest): List<AuthorResponse> =
        authorRepository.findAll(
            PageRequest.of(
                request.page,
                request.size,
                Sort.by(Sort.Direction.ASC, Author::name.name)
            ),
        ).content.map { it.toResponse() }

    override fun getAuthor(authorId: Long): AuthorResponse =
        findAuthorByIdOrThrowNotFound(authorId).toResponse()

    override fun updateAuthor(authorId: Long, request: UpdateAuthorRequest): AuthorResponse {
        val author = findAuthorByIdOrThrowNotFound(authorId)

        author.apply {
            name = request.name
        }

        return authorRepository.save(author).toResponse()
    }

    override fun deleteAuthor(authorId: Long) {
        val author = findAuthorByIdOrThrowNotFound(authorId)
        authorRepository.delete(author)
    }

    override fun getAuthorBooks(authorId: Long): List<BookPreviewResponse> {
        val author = findAuthorByIdOrThrowNotFound(authorId)
        return author.books.map { it.toPreviewResponse() }
    }

    private fun findAuthorByIdOrThrowNotFound(authorId: Long): Author =
        authorRepository.findByIdOrNull(authorId)
            ?: throw NotFoundException("Author with id $authorId not found")
}
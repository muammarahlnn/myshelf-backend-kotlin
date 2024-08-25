package com.muammarahlnn.myshelf.backend.service.impl

import com.muammarahlnn.myshelf.backend.dto.request.CreateAuthorRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdateAuthorRequest
import com.muammarahlnn.myshelf.backend.dto.response.AuthorResponse
import com.muammarahlnn.myshelf.backend.dto.response.toResponse
import com.muammarahlnn.myshelf.backend.entity.Author
import com.muammarahlnn.myshelf.backend.exception.NotFoundException
import com.muammarahlnn.myshelf.backend.repository.AuthorRepository
import com.muammarahlnn.myshelf.backend.service.AuthorService
import com.muammarahlnn.myshelf.backend.util.ValidationUtil
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
    private val validationUtil: ValidationUtil,
) : AuthorService {

    override fun createAuthor(request: CreateAuthorRequest): AuthorResponse {
        validationUtil.validate(request)

        val author = Author(name = request.name)
        return authorRepository.save(author).toResponse()
    }

    override fun getAuthors(request: PagingRequest): List<AuthorResponse> {
        validationUtil.validate(request)

        return authorRepository.findAll(
            PageRequest.of(
                request.page,
                request.size,
                Sort.by(Sort.Direction.ASC, Author::name.name)
            ),
        ).content.map { it.toResponse() }
    }

    override fun getAuthor(authorId: Long): AuthorResponse =
        findAuthorByIdOrThrowNotFound(authorId).toResponse()

    override fun updateAuthor(authorId: Long, request: UpdateAuthorRequest): AuthorResponse {
        val author = findAuthorByIdOrThrowNotFound(authorId)
        validationUtil.validate(request)

        author.apply {
            name = request.name
        }

        return authorRepository.save(author).toResponse()
    }

    private fun findAuthorByIdOrThrowNotFound(authorId: Long): Author =
        authorRepository.findByIdOrNull(authorId)
            ?: throw NotFoundException("Author with id $authorId not found")
}
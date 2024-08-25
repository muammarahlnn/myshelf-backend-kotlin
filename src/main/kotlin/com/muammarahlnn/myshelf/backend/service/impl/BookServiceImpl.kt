package com.muammarahlnn.myshelf.backend.service.impl

import com.muammarahlnn.myshelf.backend.dto.request.CreateBookRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdateBookRequest
import com.muammarahlnn.myshelf.backend.dto.response.BookResponse
import com.muammarahlnn.myshelf.backend.dto.response.toResponse
import com.muammarahlnn.myshelf.backend.entity.Book
import com.muammarahlnn.myshelf.backend.entity.Publisher
import com.muammarahlnn.myshelf.backend.exception.NotFoundException
import com.muammarahlnn.myshelf.backend.repository.BookRepository
import com.muammarahlnn.myshelf.backend.service.BookService
import com.muammarahlnn.myshelf.backend.util.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BookServiceImpl(
    private val bookRepository: BookRepository,
    private val validationUtil: ValidationUtil,
) : BookService {

    override fun createBook(request: CreateBookRequest): BookResponse {
        validationUtil.validate(request)

        val bookEntity = Book(
            title = request.title,
            desc = request.desc,
            createdAt = LocalDateTime.now(),
            publisher = Publisher(name = "test")
        )

        val savedBook = try {
            bookRepository.save(bookEntity)
        } catch (e: Exception) {
            throw IllegalStateException("Failed to save book", e)
        }

        return savedBook.toResponse()
    }

    override fun getBooks(request: PagingRequest): List<BookResponse> {
        validationUtil.validate(request)

        return bookRepository.findAll(
            PageRequest.of(
                request.page,
                request.size,
                Sort.by(Sort.Direction.DESC, Book::createdAt.name)
            ),
        ).content.map { it.toResponse() }
    }

    override fun getBook(bookId: String): BookResponse =
        findBookByIdOrThrowNotFound(bookId).toResponse()

    override fun updateBook(bookId: String, request: UpdateBookRequest): BookResponse {
        val book = findBookByIdOrThrowNotFound(bookId)
        validationUtil.validate(request)

        book.apply {
            title = request.title
            request.desc?.let { desc = it }
            updatedAt = LocalDateTime.now()
        }

        return bookRepository.save(book).toResponse()
    }

    override fun deleteBook(bookId: String) {
        val book = findBookByIdOrThrowNotFound(bookId)
        bookRepository.delete(book)
    }

    private fun findBookByIdOrThrowNotFound(bookId: String): Book =
        bookRepository.findByIdOrNull(bookId) ?: throw NotFoundException("Book with id $bookId not found")
}
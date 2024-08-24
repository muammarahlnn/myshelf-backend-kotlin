package com.muammarahlnn.myshelf.backend.service.impl

import com.muammarahlnn.myshelf.backend.entity.Book
import com.muammarahlnn.myshelf.backend.model.request.CreateBookRequest
import com.muammarahlnn.myshelf.backend.model.response.BookResponse
import com.muammarahlnn.myshelf.backend.model.response.toResponse
import com.muammarahlnn.myshelf.backend.repository.BookRepository
import com.muammarahlnn.myshelf.backend.service.BookService
import com.muammarahlnn.myshelf.backend.util.ValidationUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.Date

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
        )

        val savedBook = try {
            bookRepository.save(bookEntity)
        } catch (e: Exception) {
            throw IllegalStateException("Failed to save book", e)
        }

        return savedBook.toResponse()
    }
}
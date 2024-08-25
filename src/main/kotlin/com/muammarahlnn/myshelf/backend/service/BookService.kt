package com.muammarahlnn.myshelf.backend.service

import com.muammarahlnn.myshelf.backend.dto.request.CreateBookRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdateBookRequest
import com.muammarahlnn.myshelf.backend.dto.response.BookResponse

interface BookService {

    fun createBook(request: CreateBookRequest): BookResponse

    fun getBooks(request: PagingRequest): List<BookResponse>

    fun getBook(bookId: String): BookResponse

    fun updateBook(bookId: String, request: UpdateBookRequest): BookResponse

    fun deleteBook(bookId: String)
}
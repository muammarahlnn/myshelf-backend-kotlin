package com.muammarahlnn.myshelf.backend.service

import com.muammarahlnn.myshelf.backend.model.request.CreateBookRequest
import com.muammarahlnn.myshelf.backend.model.request.GetBooksRequest
import com.muammarahlnn.myshelf.backend.model.response.BookResponse

interface BookService {

    fun createBook(request: CreateBookRequest): BookResponse

    fun getBooks(request: GetBooksRequest): List<BookResponse>

    fun getBook(bookId: String): BookResponse
}
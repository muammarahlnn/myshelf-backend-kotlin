package com.muammarahlnn.myshelf.backend.service

import com.muammarahlnn.myshelf.backend.dto.request.CreateBookRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdateBookRequest
import com.muammarahlnn.myshelf.backend.dto.response.BookDetailsResponse
import com.muammarahlnn.myshelf.backend.dto.response.BookPreviewResponse

interface BookService {

    fun createBook(request: CreateBookRequest): BookDetailsResponse

    fun getBooks(request: PagingRequest): List<BookPreviewResponse>

    fun getBookDetails(bookId: String): BookDetailsResponse

    fun updateBook(bookId: String, request: UpdateBookRequest): BookDetailsResponse

    fun deleteBook(bookId: String)
}
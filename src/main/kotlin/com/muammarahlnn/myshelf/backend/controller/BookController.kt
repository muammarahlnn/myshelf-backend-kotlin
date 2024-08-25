package com.muammarahlnn.myshelf.backend.controller

import com.muammarahlnn.myshelf.backend.controller.provider.PagingProvider
import com.muammarahlnn.myshelf.backend.dto.request.CreateBookRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdateBookRequest
import com.muammarahlnn.myshelf.backend.dto.response.BookResponse
import com.muammarahlnn.myshelf.backend.dto.response.base.WebResponse
import com.muammarahlnn.myshelf.backend.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookController(
    private val bookService: BookService,
) : PagingProvider<BookResponse>() {

    override fun getPagedData(pagingRequest: PagingRequest): List<BookResponse> =
        bookService.getBooks(pagingRequest)

    @PostMapping
    fun createBook(@RequestBody requestBody: CreateBookRequest): WebResponse<BookResponse> =
        WebResponse.success(bookService.createBook(requestBody))

    @GetMapping("{bookId}")
    fun getBook(@PathVariable bookId: String): WebResponse<BookResponse> =
        WebResponse.success(bookService.getBook(bookId))

    @PutMapping("{bookId}")
    fun updateBook(
        @PathVariable bookId: String,
        @RequestBody requestBody: UpdateBookRequest,
    ): WebResponse<BookResponse> = WebResponse.success(
        bookService.updateBook(
            bookId = bookId,
            request = requestBody,
        )
    )
}
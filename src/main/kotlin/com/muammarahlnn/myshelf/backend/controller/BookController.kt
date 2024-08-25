package com.muammarahlnn.myshelf.backend.controller

import com.muammarahlnn.myshelf.backend.model.request.CreateBookRequest
import com.muammarahlnn.myshelf.backend.model.request.GetBooksRequest
import com.muammarahlnn.myshelf.backend.model.response.BookResponse
import com.muammarahlnn.myshelf.backend.model.response.base.WebResponse
import com.muammarahlnn.myshelf.backend.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookController(
    private val bookService: BookService,
) {

    @PostMapping
    fun createBook(@RequestBody requestBody: CreateBookRequest): WebResponse<BookResponse> =
        WebResponse.success(bookService.createBook(requestBody))

    @GetMapping
    fun getBooks(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
    ): WebResponse<List<BookResponse>> {
        val request = GetBooksRequest(
            page = page,
            size = size,
        )
        return WebResponse.success(bookService.getBooks(request))
    }

    @GetMapping("{bookId}")
    fun getBook(@PathVariable bookId: String): WebResponse<BookResponse> =
        WebResponse.success(bookService.getBook(bookId))
}
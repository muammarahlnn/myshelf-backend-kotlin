package com.muammarahlnn.myshelf.backend.controller

import com.muammarahlnn.myshelf.backend.model.request.CreateBookRequest
import com.muammarahlnn.myshelf.backend.model.response.BookResponse
import com.muammarahlnn.myshelf.backend.model.response.base.WebResponse
import com.muammarahlnn.myshelf.backend.service.BookService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookController(
    private val bookService: BookService,
) {

    @PostMapping
    fun createBook(@RequestBody requestBody: CreateBookRequest): WebResponse<BookResponse> =
        WebResponse.success(bookService.createBook(requestBody))
}
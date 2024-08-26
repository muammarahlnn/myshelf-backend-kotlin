package com.muammarahlnn.myshelf.backend.controller

import com.muammarahlnn.myshelf.backend.controller.provider.PagingProvider
import com.muammarahlnn.myshelf.backend.dto.request.CreateBookRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdateBookRequest
import com.muammarahlnn.myshelf.backend.dto.response.BookDetailsResponse
import com.muammarahlnn.myshelf.backend.dto.response.BookPreviewResponse
import com.muammarahlnn.myshelf.backend.dto.response.base.WebResponse
import com.muammarahlnn.myshelf.backend.service.BookService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("books")
class BookController(
    private val bookService: BookService,
) : PagingProvider<BookPreviewResponse>() {

    override fun getPagedData(pagingRequest: PagingRequest): List<BookPreviewResponse> =
        bookService.getBooks(pagingRequest)

    @PostMapping
    fun createBook(@RequestBody requestBody: CreateBookRequest): WebResponse<BookDetailsResponse> =
        WebResponse.success(bookService.createBook(requestBody))

    @GetMapping("{bookId}")
    fun getBookDetails(@PathVariable bookId: String): WebResponse<BookDetailsResponse> =
        WebResponse.success(bookService.getBookDetails(bookId))

    @PutMapping("{bookId}")
    fun updateBook(
        @PathVariable bookId: String,
        @RequestBody requestBody: UpdateBookRequest,
    ): WebResponse<BookDetailsResponse> = WebResponse.success(
        bookService.updateBook(
            bookId = bookId,
            request = requestBody,
        )
    )

    @DeleteMapping("{bookId}")
    fun deleteBook(@PathVariable bookId: String): WebResponse<String> {
        bookService.deleteBook(bookId)
        return WebResponse.success("Book with id $bookId successfully deleted")
    }
}
package com.muammarahlnn.myshelf.backend.service.impl

import com.muammarahlnn.myshelf.backend.controller.provider.PagingProvider
import com.muammarahlnn.myshelf.backend.dto.request.CreateBookRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdateBookRequest
import com.muammarahlnn.myshelf.backend.dto.response.BookDetailsResponse
import com.muammarahlnn.myshelf.backend.dto.response.BookPreviewResponse
import com.muammarahlnn.myshelf.backend.dto.response.toDetailsResponse
import com.muammarahlnn.myshelf.backend.dto.response.toPreviewResponse
import com.muammarahlnn.myshelf.backend.entity.Author
import com.muammarahlnn.myshelf.backend.entity.Book
import com.muammarahlnn.myshelf.backend.entity.Category
import com.muammarahlnn.myshelf.backend.exception.NotFoundException
import com.muammarahlnn.myshelf.backend.repository.AuthorRepository
import com.muammarahlnn.myshelf.backend.repository.BookRepository
import com.muammarahlnn.myshelf.backend.repository.CategoryRepository
import com.muammarahlnn.myshelf.backend.repository.PublisherRepository
import com.muammarahlnn.myshelf.backend.service.BookService
import com.muammarahlnn.myshelf.backend.service.UserService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@Service
class BookServiceImpl(
    private val bookRepository: BookRepository,
    private val publisherRepository: PublisherRepository,
    private val authorRepository: AuthorRepository,
    private val categoryRepository: CategoryRepository,
    private val userService: UserService,
) : BookService {

    override fun createBook(request: CreateBookRequest): BookDetailsResponse {
        val book = Book(
            title = request.title,
            desc = request.desc,
            createdAt = LocalDateTime.now(),
            user = userService.getCurrentUser(),
        ).apply {
            addAuthors(request.authorIds)
            addCategories(request.categoryIds)
            addPublisher(request.publisherId)
        }

        return bookRepository.save(book).toDetailsResponse()
    }

    override fun getBooks(request: PagingRequest): List<BookPreviewResponse> {
        val userId = userService.getCurrentUser().id ?: return emptyList()
        return bookRepository.findByUserId(
            userId = userId,
            pageable = PageRequest.of(
                request.page ?: PagingProvider.DEFAULT_PAGE,
                request.size ?: PagingProvider.DEFAULT_SIZE,
                Sort.by(Sort.Direction.DESC, Book::createdAt.name)
            )
        ).content.map { it.toPreviewResponse() }
    }

    override fun getBookDetails(bookId: String): BookDetailsResponse =
        findBookByIdOrThrowNotFound(bookId).toDetailsResponse()

    override fun updateBook(bookId: String, request: UpdateBookRequest): BookDetailsResponse {
        val book = findBookByIdOrThrowNotFound(bookId)
        book.apply {
            title = request.title
            request.desc?.let { desc = it }
            addAuthors(request.authorIds)
            addCategories(request.categoryIds)
            addPublisher(request.publisherId)
            updatedAt = LocalDateTime.now()
        }

        return bookRepository.save(book).toDetailsResponse()
    }

    override fun deleteBook(bookId: String) {
        val book = findBookByIdOrThrowNotFound(bookId)
        bookRepository.delete(book)
    }

    override fun updateBookImage(bookId: String, image: MultipartFile): BookDetailsResponse {
        val book = findBookByIdOrThrowNotFound(bookId)
        book.apply {
            this.image = image.bytes.toList()
        }

        return bookRepository.save(book).toDetailsResponse()
    }

    private fun findBookByIdOrThrowNotFound(bookId: String): Book =
        bookRepository.findByIdOrNull(bookId) ?: throw NotFoundException("Book with id $bookId not found")

    private fun Book.addPublisher(publisherId: Long?): Book = apply {
        publisherId?.let { publisherId ->
            publisherRepository.findByIdOrNull(publisherId)?.let { publisher ->
                this.publisher = publisher
            } ?: throw NotFoundException("Publisher with id $publisherId not found")
        }
    }

    private fun Book.addAuthors(authorIds: List<Long>?): Book = apply {
        authorIds?.let { authorIds ->
            val authors = mutableSetOf<Author>()
            authorIds.forEach { authorId ->
                val author = authorRepository.findByIdOrNull(authorId)
                    ?: throw NotFoundException("Author with id $authorId not found")
                authors.add(author)
            }
            this.authors = authors
        }
    }

    private fun Book.addCategories(categoryIds: List<Long>?): Book = apply {
        categoryIds?.let { categoryIds ->
            val categories = mutableSetOf<Category>()
            categoryIds.forEach { categoryId ->
                val category = categoryRepository.findByIdOrNull(categoryId)
                    ?: throw NotFoundException("Category with id $categoryId not found")
                categories.add(category)
            }
            this.categories = categories
        }
    }
}
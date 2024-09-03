package com.muammarahlnn.myshelf.backend.service.impl

import com.muammarahlnn.myshelf.backend.controller.provider.PagingProvider
import com.muammarahlnn.myshelf.backend.dto.request.CreatePublisherRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdatePublisherRequest
import com.muammarahlnn.myshelf.backend.dto.response.BookPreviewResponse
import com.muammarahlnn.myshelf.backend.dto.response.PublisherResponse
import com.muammarahlnn.myshelf.backend.dto.response.toPreviewResponse
import com.muammarahlnn.myshelf.backend.dto.response.toResponse
import com.muammarahlnn.myshelf.backend.entity.Publisher
import com.muammarahlnn.myshelf.backend.exception.NotFoundException
import com.muammarahlnn.myshelf.backend.repository.PublisherRepository
import com.muammarahlnn.myshelf.backend.service.PublisherService
import com.muammarahlnn.myshelf.backend.service.UserService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 * @Author Muammar Ahlan Abimanyu
 * @File PublisherServiceImpl.kt, 26/08/2024 00.57
 */
@Service
class PublisherServiceImpl(
    private val publisherRepository: PublisherRepository,
    private val userService: UserService,
) : PublisherService {

    override fun createPublisher(request: CreatePublisherRequest): PublisherResponse {
        val publisher = Publisher(
            name = request.name,
            user = userService.getCurrentUser(),
        )
        return publisherRepository.save(publisher).toResponse()
    }

    override fun getPublishers(request: PagingRequest): List<PublisherResponse> {
        val userId = userService.getCurrentUser().id ?: return emptyList()
        return publisherRepository.findByUserId(
            userId = userId,
            pageable = PageRequest.of(
                request.page ?: PagingProvider.DEFAULT_PAGE,
                request.size ?: PagingProvider.DEFAULT_SIZE,
                Sort.by(Sort.Direction.ASC, Publisher::name.name)
            ),
        ).content.map { it.toResponse() }
    }


    override fun getPublisher(publisherId: Long): PublisherResponse =
        findPublisherByIdOrThrowNotFound(publisherId).toResponse()

    override fun updatePublisher(publisherId: Long, request: UpdatePublisherRequest): PublisherResponse {
        val publisher = findPublisherByIdOrThrowNotFound(publisherId)
        publisher.apply {
            name = request.name
        }

        return publisherRepository.save(publisher).toResponse()
    }

    override fun deletePublisher(publisherId: Long) {
        val publisher = findPublisherByIdOrThrowNotFound(publisherId)
        publisherRepository.delete(publisher)
    }

    override fun getPublisherBooks(publisherId: Long): List<BookPreviewResponse> {
        val publisher = findPublisherByIdOrThrowNotFound(publisherId)
        return publisher.books.map { it.toPreviewResponse() }
    }

    private fun findPublisherByIdOrThrowNotFound(publisherId: Long): Publisher =
        publisherRepository.findByIdOrNull(publisherId)
            ?: throw NotFoundException("Publisher with id $publisherId not found")
}
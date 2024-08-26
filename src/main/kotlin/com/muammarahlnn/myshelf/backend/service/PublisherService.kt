package com.muammarahlnn.myshelf.backend.service

import com.muammarahlnn.myshelf.backend.dto.request.CreatePublisherRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdatePublisherRequest
import com.muammarahlnn.myshelf.backend.dto.response.BookResponse
import com.muammarahlnn.myshelf.backend.dto.response.PublisherResponse

/**
 * @Author Muammar Ahlan Abimanyu
 * @File PublisherService.kt, 26/08/2024 00.41
 */
interface PublisherService {

    fun createPublisher(request: CreatePublisherRequest): PublisherResponse

    fun getPublishers(request: PagingRequest): List<PublisherResponse>

    fun getPublisher(publisherId: Long): PublisherResponse

    fun updatePublisher(publisherId: Long, request: UpdatePublisherRequest): PublisherResponse

    fun deletePublisher(publisherId: Long)

    fun getPublisherBooks(publisherId: Long): List<BookResponse>
}
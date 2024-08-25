package com.muammarahlnn.myshelf.backend.controller

import com.muammarahlnn.myshelf.backend.controller.provider.PagingProvider
import com.muammarahlnn.myshelf.backend.dto.request.CreatePublisherRequest
import com.muammarahlnn.myshelf.backend.dto.request.PagingRequest
import com.muammarahlnn.myshelf.backend.dto.request.UpdatePublisherRequest
import com.muammarahlnn.myshelf.backend.dto.response.PublisherResponse
import com.muammarahlnn.myshelf.backend.dto.response.base.WebResponse
import com.muammarahlnn.myshelf.backend.service.PublisherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @Author Muammar Ahlan Abimanyu
 * @File PublisherController.kt, 26/08/2024 01.01
 */
@RestController
@RequestMapping("publishers")
class PublisherController(
    private val publisherService: PublisherService,
) : PagingProvider<PublisherResponse>() {

    override fun getPagedData(pagingRequest: PagingRequest): List<PublisherResponse> =
        publisherService.getPublishers(pagingRequest)

    @PostMapping
    fun createPublisher(@RequestBody requestBody: CreatePublisherRequest): WebResponse<PublisherResponse> =
        WebResponse.success(publisherService.createPublisher(requestBody))

    @GetMapping("{publisherId}")
    fun getPublisher(@PathVariable publisherId: Long): WebResponse<PublisherResponse> =
        WebResponse.success(publisherService.getPublisher(publisherId))

    @PutMapping("{publisherId}")
    fun updatePublisher(
        @PathVariable publisherId: Long,
        @RequestBody requestBody: UpdatePublisherRequest,
    ): WebResponse<PublisherResponse> = WebResponse.success(
        publisherService.updatePublisher(
            publisherId = publisherId,
            request = requestBody,
        )
    )
}
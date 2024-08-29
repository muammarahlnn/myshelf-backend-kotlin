package com.muammarahlnn.myshelf.backend.dto.response.base

import org.springframework.http.HttpStatus

data class WebResponse<T>(
    val code: Int,
    val status: String,
    val data: T,
) {

    companion object {

        fun <T> success(data: T): WebResponse<T> = WebResponse(
            code = HttpStatus.ACCEPTED.value(),
            status = HttpStatus.ACCEPTED.reasonPhrase,
            data = data,
        )

        fun fail(
            httpStatus: HttpStatus,
            message: String,
        ): WebResponse<String> = WebResponse(
            code = httpStatus.value(),
            status = httpStatus.reasonPhrase,
            data = message,
        )
    }
}
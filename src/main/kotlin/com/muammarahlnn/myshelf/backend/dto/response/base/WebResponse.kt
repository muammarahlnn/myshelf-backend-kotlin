package com.muammarahlnn.myshelf.backend.dto.response.base

data class WebResponse<T>(
    val code: Int,
    val status: String,
    val data: T,
) {

    companion object {

        fun <T> success(data: T): WebResponse<T> = WebResponse(
            code = 200,
            status = "SUCCESS",
            data = data,
        )
    }
}
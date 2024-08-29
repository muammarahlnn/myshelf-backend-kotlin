package com.muammarahlnn.myshelf.backend.controller

import com.muammarahlnn.myshelf.backend.dto.response.base.WebResponse
import com.muammarahlnn.myshelf.backend.exception.InternalServerException
import com.muammarahlnn.myshelf.backend.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @Author Muammar Ahlan Abimanyu
 * @File ErrorController.kt, 24/08/2024 23.08
 */
@RestControllerAdvice
class ErrorController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableHandler(exception: HttpMessageNotReadableException): WebResponse<String> {
        val pattern = "JSON property (\\S+)".toRegex()
        val missingProperty = pattern.find(exception.message.toString())?.groupValues?.get(1)
        val errorMessage = missingProperty?.let {
            "$it property is missing"
        } ?: "Malformed JSON request"

        return WebResponse.fail(
            httpStatus = HttpStatus.BAD_REQUEST,
            message = errorMessage,
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidHandler(exception: MethodArgumentNotValidException): WebResponse<String> {
        val errorMessage = exception.bindingResult.fieldErrors.joinToString(separator = ", ") { fieldError ->
            "${fieldError.field}: ${fieldError.defaultMessage}"
        }
        return WebResponse.fail(
            httpStatus = HttpStatus.BAD_REQUEST,
            message = errorMessage
        )
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundHandler(exception: NotFoundException): WebResponse<String> =
        WebResponse.fail(
            httpStatus = HttpStatus.NOT_FOUND,
            message = exception.message.toString(),
        )

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = [InternalServerException::class])
    fun internalServerHandler(exception: InternalServerException): WebResponse<String> =
        WebResponse.fail(
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
            message = exception.message.toString(),
        )
}
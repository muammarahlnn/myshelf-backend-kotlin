package com.muammarahlnn.myshelf.backend.controller

import com.muammarahlnn.myshelf.backend.exception.NotFoundException
import com.muammarahlnn.myshelf.backend.dto.response.base.WebResponse
import com.muammarahlnn.myshelf.backend.exception.InternalServerException
import jakarta.validation.ConstraintViolationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @Author Muammar Ahlan Abimanyu
 * @File ErrorController.kt, 24/08/2024 23.08
 */
@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): WebResponse<String> =
        WebResponse(
            code = 200,
            status = "BAD REQUEST",
            data = constraintViolationException.message.toString(),
        )

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundHandler(notFoundException: NotFoundException): WebResponse<String> =
        WebResponse(
            code = 404,
            status = "NOT FOUND",
            data = notFoundException.message.toString(),
        )

    @ExceptionHandler(value = [InternalServerException::class])
    fun notFoundHandler(internalServerException: InternalServerException): WebResponse<String> =
        WebResponse(
            code = 500,
            status = "INTERNAL SERVER ERROR",
            data = internalServerException.message.toString(),
        )
}
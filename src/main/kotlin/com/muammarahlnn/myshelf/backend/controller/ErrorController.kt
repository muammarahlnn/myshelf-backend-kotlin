package com.muammarahlnn.myshelf.backend.controller

import com.muammarahlnn.myshelf.backend.model.response.base.WebResponse
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
}
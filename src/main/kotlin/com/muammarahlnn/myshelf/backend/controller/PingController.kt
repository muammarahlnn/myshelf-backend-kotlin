package com.muammarahlnn.myshelf.backend.controller

import com.muammarahlnn.myshelf.backend.dto.response.base.WebResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("ping")
class PingController {

    @GetMapping
    fun ping(): WebResponse<String> = WebResponse.success("UP")
}
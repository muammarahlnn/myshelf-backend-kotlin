package com.muammarahlnn.myshelf.backend.service.impl

import com.muammarahlnn.myshelf.backend.entity.User
import com.muammarahlnn.myshelf.backend.service.UserService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

/**
 * @Author Muammar Ahlan Abimanyu
 * @File UserServiceImpl.kt, 02/09/2024 16.42
 */
@Service
class UserServiceImpl : UserService {

    override fun getCurrentUser(): User =
        SecurityContextHolder.getContext().authentication.principal as User
}
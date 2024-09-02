package com.muammarahlnn.myshelf.backend.service

import com.muammarahlnn.myshelf.backend.entity.User

/**
 * @Author Muammar Ahlan Abimanyu
 * @File UserService.kt, 02/09/2024 16.42
 */
interface UserService {

    fun getCurrentUser(): User
}
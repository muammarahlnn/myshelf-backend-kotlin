package com.muammarahlnn.myshelf.backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "books")
data class Book(
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36)
    val id: String = UUID.randomUUID().toString(),

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "description", nullable = true)
    var desc: String? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,

    @Column(name = "updated_at", nullable = true)
    var updatedAt: LocalDateTime? = null,
)
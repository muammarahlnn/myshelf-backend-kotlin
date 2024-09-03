package com.muammarahlnn.myshelf.backend.entity

import jakarta.persistence.*
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
    var title: String?,

    @Column(
        name = "description",
        nullable = true,
        columnDefinition = "TEXT"
    )
    var desc: String? = null,

    @Lob
    @Column(name = "image", nullable = true)
    var image: List<Byte>? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,

    @Column(name = "updated_at", nullable = true)
    var updatedAt: LocalDateTime? = null,

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = true)
    var publisher: Publisher? = null,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,
) {
    @ManyToMany
    @JoinTable(
        name = "book_author",
        joinColumns = [JoinColumn(name = "book_id")],
        inverseJoinColumns = [JoinColumn(name = "author_id")]
    )
    var authors: Set<Author> = mutableSetOf()

    @ManyToMany
    @JoinTable(
        name = "book_category",
        joinColumns = [JoinColumn(name = "book_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")],
    )
    var categories: Set<Category> = mutableSetOf()
}
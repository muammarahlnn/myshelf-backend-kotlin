package com.muammarahlnn.myshelf.backend.entity

import jakarta.persistence.*

/**
 * @Author Muammar Ahlan Abimanyu
 * @File Author.kt, 25/08/2024 21.27
 */
@Entity
@Table(name = "authors")
data class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String,
) {
    @ManyToMany(mappedBy = "authors")
    val books: Set<Book> = mutableSetOf()
}
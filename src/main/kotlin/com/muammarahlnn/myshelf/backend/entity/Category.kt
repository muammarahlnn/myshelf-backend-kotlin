package com.muammarahlnn.myshelf.backend.entity

import jakarta.persistence.*

/**
 * @Author Muammar Ahlan Abimanyu
 * @File Category.kt, 25/08/2024 21.00
 */
@Entity
@Table(name = "categories")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String?,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,
) {
    @ManyToMany(mappedBy = "categories")
    val books: Set<Book> = mutableSetOf()
}
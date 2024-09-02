package com.muammarahlnn.myshelf.backend.entity

import jakarta.persistence.*

/**
 * @Author Muammar Ahlan Abimanyu
 * @File Publisher.kt, 25/08/2024 19.08
 */
@Entity
@Table(name = "publishers")
data class Publisher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,
) {
    @OneToMany(
        mappedBy = "publisher",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    val books: Set<Book> = mutableSetOf()
}
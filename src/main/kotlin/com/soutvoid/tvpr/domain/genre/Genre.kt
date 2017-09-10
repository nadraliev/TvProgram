package com.soutvoid.tvpr.domain.genre

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "genre")
data class Genre(
        var name: String = "",
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
): Serializable
package com.soutvoid.tvpr.domain.genre

import com.soutvoid.tvpr.domain.show.TvShow
import jdk.nashorn.internal.ir.annotations.Ignore
import java.io.Serializable
import javax.persistence.*

@Entity
data class Genre(
        var name: String = "",
        @OneToMany(mappedBy = "genre", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        @Ignore
        var tvShows: MutableSet<TvShow> = mutableSetOf(),
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
): Serializable
package com.soutvoid.tvpr.domain.schedule

import com.soutvoid.tvpr.domain.show.TvShow
import java.io.Serializable
import javax.persistence.*

@Entity
data class ChannelSchedule(
        @OneToMany(mappedBy = "schedule", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        @OrderBy("startTime ASC")
        var tvShows: MutableList<TvShow> = mutableListOf(),
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
): Serializable
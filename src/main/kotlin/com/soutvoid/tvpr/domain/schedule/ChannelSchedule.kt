package com.soutvoid.tvpr.domain.schedule

import com.soutvoid.tvpr.domain.channel.Channel
import com.soutvoid.tvpr.domain.show.Show
import java.io.Serializable
import javax.persistence.*

@Entity
data class ChannelSchedule(
        @OneToMany(mappedBy = "schedule", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        @OrderBy("startTime ASC")
        var shows: MutableList<Show> = mutableListOf(),
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
): Serializable
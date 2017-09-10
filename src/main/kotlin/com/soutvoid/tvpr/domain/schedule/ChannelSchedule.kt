package com.soutvoid.tvpr.domain.schedule

import com.soutvoid.tvpr.domain.show.Show
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "schedule")
data class ChannelSchedule(
        @ElementCollection
        var shows: MutableList<Show> = mutableListOf(),
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
): Serializable
package com.soutvoid.tvpr.domain.channel

import com.soutvoid.tvpr.domain.schedule.ChannelSchedule
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "channel")
data class Channel(
        var name: String = "",
        var schedule: ChannelSchedule = ChannelSchedule(),
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
): Serializable
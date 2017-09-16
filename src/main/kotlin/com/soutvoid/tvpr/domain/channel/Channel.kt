package com.soutvoid.tvpr.domain.channel

import com.soutvoid.tvpr.domain.schedule.ChannelSchedule
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "channels")
data class Channel(
        var name: String = "",
        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        var schedule: ChannelSchedule? = ChannelSchedule(),
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "channel_id")
        var id: Long = 0
): Serializable
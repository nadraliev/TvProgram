package com.soutvoid.tvpr.domain.schedule

import com.soutvoid.tvpr.domain.channel.Channel
import com.soutvoid.tvpr.domain.show.Show
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "schedule")
data class ChannelSchedule(
        @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        @OrderBy("startTime ASC")
        var shows: MutableSet<Show> = mutableSetOf(),
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "schedule_id")
        var id: Long = 0
): Serializable
package com.soutvoid.tvpr.domain.show

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "show")
class Show(
        var name: String = "",
        var dayOfWeek: Int = 0,
        var startTime: Long = 0,    //in minutes
        var endTime: Long = 0,  //in minutes
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
): Serializable
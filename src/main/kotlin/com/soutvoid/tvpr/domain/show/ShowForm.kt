package com.soutvoid.tvpr.domain.show

import java.io.Serializable

data class ShowForm(
        var name: String = "",
        var dayOfWeek: Int = 0,
        var startTime: String = "00:00",
        var endTime: String = "00:00",
        var genreName: String = ""
) : Serializable {
    fun getShow(): Show =
            Show(
                    name,
                    dayOfWeek,
                    getMinutes(startTime).toLong(),
                    getMinutes(endTime).toLong(),
                    genreName
            )

    fun getMinutes(time: String): Int =
            time.substring(0, 2).toInt() * 60 + time.substring(3, 4).toInt()
}
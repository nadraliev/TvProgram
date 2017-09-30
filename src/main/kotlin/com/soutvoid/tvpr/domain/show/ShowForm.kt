package com.soutvoid.tvpr.domain.show

import com.soutvoid.tvpr.domain.genre.Genre
import java.io.Serializable

data class ShowForm(
        var name: String = "",
        var dayOfWeek: Int = 0,
        var startTime: String = "00:00",
        var endTime: String = "00:00",
        var genreName: String = ""
) : Serializable {
    fun getShow(allGenres: List<Genre>): Show =
            Show(
                    name,
                    dayOfWeek,
                    getMinutes(startTime).toLong(),
                    getMinutes(endTime).toLong(),
                    allGenres.find { it.name == genreName }
            )

    fun getMinutes(time: String): Int =
            time.substring(0, 2).toInt() * 60 + time.substring(3, 5).toInt()
}
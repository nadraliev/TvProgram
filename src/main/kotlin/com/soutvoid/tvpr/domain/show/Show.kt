package com.soutvoid.tvpr.domain.show

import com.soutvoid.tvpr.domain.genre.Genre
import org.springframework.web.bind.annotation.ModelAttribute
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "show")
class Show(
        var name: String = "",
        var dayOfWeek: Int = 0,
        var startTime: Long = 0,    //in minutes
        var endTime: Long = 0,  //in minutes
        @ManyToOne
        var genre: Genre? = null,
        var channelId: Long = 0,
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "show_id")
        var id: Long = 0
) : Serializable {

    /**
     * @param[minutes] minutes since midnight
     * @return formatted string. example: 15:32
     */
    fun getFormattedTime(minutes: Long): String =
            "${String.format("%02d", minutes / 60)}:${String.format("%02d", minutes % 60)}"

}
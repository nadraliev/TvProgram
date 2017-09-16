package com.soutvoid.tvpr.controller

import com.soutvoid.tvpr.domain.channel.Channel
import com.soutvoid.tvpr.domain.genre.Genre
import com.soutvoid.tvpr.domain.genre.GenresRepository
import com.soutvoid.tvpr.domain.schedule.ChannelSchedule
import com.soutvoid.tvpr.domain.show.Show
import com.soutvoid.tvpr.domain.show.ShowForm
import com.soutvoid.tvpr.domain.show.ShowsRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.time.DayOfWeek
import java.util.*
import javax.inject.Inject

@RequestMapping("/")
@Controller
class HomeController @Inject constructor(
        var genresRepository: GenresRepository,
        var showsRepository: ShowsRepository) {

    @ModelAttribute("genres")
    fun genres(): List<Genre> = genresRepository.findAll().toList()

    @RequestMapping("/genres")
    @ResponseBody
    fun allGenres(model: Model): List<Genre> = genresRepository.findAll().toList()

    @ModelAttribute("channels")
    fun programs(): List<Channel> =
            listOf(Channel(
                    "Test channel",
                    ChannelSchedule(
                            showsRepository.findAll().sortedBy { it.startTime }.toMutableList()
                    )
            ))

    @RequestMapping("/channel")
    fun channel(model: Model): String {
        return "fragments/channel :: channel"
    }

    @ModelAttribute("weekDays")
    fun weekDays(): List<String> =
            DayOfWeek.values().map { it.toString().capitalize() }

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun index(model: Model) : String {
        return "index"
    }

    @RequestMapping("/newGenre", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun newGenre(@RequestBody name: String): Boolean {
        genresRepository.save(Genre(name))
        return true
    }

    @RequestMapping("/deleteGenre", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun deleteGenre(@RequestBody index: String): Boolean {
        genresRepository.delete(genresRepository.findAll().toList()[index.toInt()])
        return true
    }

    @RequestMapping("/newShow",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    @ResponseBody
    fun newShow(@ModelAttribute("ShowForm") showForm: ShowForm): Boolean {
        showsRepository.save(showForm.getShow())
        return true
    }

    @RequestMapping("/deleteShow", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun deleteShow(@RequestBody id: String): Boolean {
        showsRepository.delete(id.toLong())
        return true
    }

}
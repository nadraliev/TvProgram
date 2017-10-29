package com.soutvoid.tvpr.controller

import com.soutvoid.tvpr.domain.channel.Channel
import com.soutvoid.tvpr.domain.channel.ChannelsRepository
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
        var showsRepository: ShowsRepository,
        var channelsRepository: ChannelsRepository) {
    /**
     * @return list of all genres in db
     * @see Genre
     */
    @ModelAttribute("genres")
    fun genres(): List<Genre> = genresRepository.findAll().toList()

    /**
     * @return list of all channels in db
     * @see Channel
     */
    @ModelAttribute("channels")
    fun programs(): List<Channel> =
            channelsRepository.findAll().toList()

    /**
     * @return list of all week days
     * @see DayOfWeek
     */
    @ModelAttribute("weekDays")
    fun weekDays(): List<String> =
            DayOfWeek.values().map { it.toString().capitalize() }

    /**
     * index
     */
    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun index(model: Model): String {
        return "index"
    }

    /**
     * endpoint to get all genres in db
     * @see Genre
     */
    @RequestMapping("/genres", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun allGenres(model: Model): List<Genre> = genresRepository.findAll().toList()

    /**
     * render template with one channels
     * @return fragment with channels
     * @see fragments/channels.html
     */
    @RequestMapping("/channels", method = arrayOf(RequestMethod.POST))
    fun channels(model: Model): String {
        return "fragments/channels :: channels"
    }

    /**
     * add new genre to db
     * @param[name] name of new genre
     * @return operation successful
     * @see Genre
     */
    @RequestMapping("/newGenre", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun newGenre(@RequestBody name: String): Boolean {
        genresRepository.save(Genre(name))
        return true
    }

    /**
     * delete genre from db
     * @param[index] index of genre to remove
     * @return operation successful
     * @see Genre
     */
    @RequestMapping("/deleteGenre", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun deleteGenre(@RequestBody index: String): Boolean {
        val genre = genresRepository.findAll().toList()[index.toInt()]
        val shows = showsRepository.findAll().filter { it.genre?.equals(genre) ?: false }
        shows.forEach {
            var channel = channelsRepository.findOne(it.channelId)
            channel.schedule?.shows?.removeIf { it.id == it.id }
            channelsRepository.save(channel)
        }
        genresRepository.delete(genre)
        return true
    }

    /**
     * add new show to db
     * @param[showForm] form from user with information about new show
     * @param[id] id of channel to add show to
     * @return operation successful
     * @see Show
     */
    @RequestMapping("/newShow/{id}",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    @ResponseBody
    fun newShow(
            @PathVariable id: String,
            @ModelAttribute("ShowForm") showForm: ShowForm): Boolean {
        var channel = channelsRepository.findOne(id.toLong())
        var show = showForm.getShow(genres())
        show.channelId = channel.id
        channel?.schedule?.shows?.add(show)
        channelsRepository.save(channel)
        return true
    }

    /**
     * delete show from db
     * @param[ids] id of channels to delete show from, id of show to delete separated with space. example: "1 5"
     * @return operation successful
     * @see Channel
     * @see Show
     */
    @RequestMapping("/deleteShow", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun deleteShow(@RequestBody ids: String): Boolean {
        var idsArray = ids.split(" ")
        var channelId = idsArray.first().toLong()
        var showId = idsArray.last().toLong()
        var channel = channelsRepository.findOne(channelId)
        channel.schedule?.shows?.removeIf { it.id == showId }
        channelsRepository.save(channel)
        return true
    }

    /**
     * add new channels to db
     * @param[name] name of new channels
     * @return operation successful
     * @see Channel
     */
    @RequestMapping("/newChannel", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun newChannel(@RequestBody name: String): Boolean {
        channelsRepository.save(Channel(name))
        return true
    }

    /**
     * delete channel from db
     * @param[id] id of channel to delete
     * @return operation successful
     * @see Channel
     */
    @RequestMapping("/deleteChannel", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun deleteChannel(@RequestBody id: String): Boolean {
        channelsRepository.delete(id.toLong())
        return true
    }

}
package com.soutvoid.tvpr.controller

import com.soutvoid.tvpr.domain.channel.Channel
import com.soutvoid.tvpr.domain.channel.ChannelsRepository
import com.soutvoid.tvpr.domain.genre.Genre
import com.soutvoid.tvpr.domain.genre.GenresRepository
import com.soutvoid.tvpr.domain.schedule.ChannelSchedule
import com.soutvoid.tvpr.domain.show.Show
import com.soutvoid.tvpr.domain.show.ShowForm
import com.soutvoid.tvpr.domain.show.ShowsRepository
import com.soutvoid.tvpr.util.validate
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
    fun channels(): List<Channel> =
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
    @RequestMapping("/genres/names", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun allGenresNames(model: Model): List<String> = genresRepository.findAll().map { it.name }.toList()

    @RequestMapping("genres", method = arrayOf(RequestMethod.GET))
    fun genres(model: Model): String =
            "fragments/genres::genres"


    /**
     * render template with channels
     * @return fragment with channels
     * @see templates/fragments/channels.html
     */
    @RequestMapping("/channels", method = arrayOf(RequestMethod.POST))
    fun channels(model: Model): String =
            "fragments/channels :: channels"

    /**
     * add new genre to db
     * @param[name] name of new genre
     * @return operation successful
     * @see Genre
     */
    @RequestMapping("/genres", method = arrayOf(RequestMethod.PUT))
    @ResponseBody
    fun newGenre(@RequestBody name: String): Boolean {
        if (name.validate() && genres().find { it.name == name } == null)
            genresRepository.save(Genre(name))
        return true
    }

    /**
     * delete genre from db
     * @param[index] index of genre to remove
     * @return operation successful
     * @see Genre
     */
    @RequestMapping("/genres/{index}", method = arrayOf(RequestMethod.DELETE))
    @ResponseBody
    fun deleteGenre(@PathVariable index: String): Boolean {
        val genre = genresRepository.findAll().toList()[index.toInt()]
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
    @RequestMapping("/channels/{id}/shows",
            method = arrayOf(RequestMethod.PUT),
            consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    @ResponseBody
    fun newShow(
            @PathVariable id: String,
            @ModelAttribute("ShowForm") showForm: ShowForm): Boolean {
        var channel = channelsRepository.findOne(id.toLong())
        if (showForm.validate(genres())) {
            val show = showForm.getShow(genres())
            show.schedule = channel.schedule
            showsRepository.save(show)
        }
        return true
    }

    /**
     * delete show from db
     * @param[id] id of show to delete
     * @return operation successful
     * @see Channel
     * @see Show
     */
    @RequestMapping("/channels/{id}/shows/{id}", method = arrayOf(RequestMethod.DELETE))
    @ResponseBody
    fun deleteShow(@PathVariable id: String): Boolean {
        showsRepository.delete(id.toLong())
        return true
    }

    /**
     * add new channels to db
     * @param[name] name of new channels
     * @return operation successful
     * @see Channel
     */
    @RequestMapping("/channels", method = arrayOf(RequestMethod.PUT))
    @ResponseBody
    fun newChannel(@RequestBody name: String): Boolean {
        if (name.validate() && channelsRepository.findAll().find { it.name == name } == null)
            channelsRepository.save(Channel(name))
        return true
    }

    /**
     * delete channel from db
     * @param[id] id of channel to delete
     * @return operation successful
     * @see Channel
     */
    @RequestMapping("/channels/{id}", method = arrayOf(RequestMethod.DELETE))
    @ResponseBody
    fun deleteChannel(@PathVariable id: String): Boolean {
        channelsRepository.delete(id.toLong())
        return true
    }

}
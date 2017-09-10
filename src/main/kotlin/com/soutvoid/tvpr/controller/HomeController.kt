package com.soutvoid.tvpr.controller

import com.soutvoid.tvpr.domain.genre.Genre
import com.soutvoid.tvpr.domain.genre.GenresRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.inject.Inject

@RequestMapping("/")
@Controller
class HomeController @Inject constructor(var genresRepository: GenresRepository) {

    @ModelAttribute("genres")
    fun genres(): List<Genre> = genresRepository.findAll().toList()

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun index(model: Model, @RequestParam(name = "name", required = false, defaultValue = "me") name: String) : String {
        model.addAttribute("name", name)
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


}
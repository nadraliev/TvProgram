package com.soutvoid.tvpr.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

@RequestMapping("/")
@Controller
class HomeController {

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun index(model: Model, @RequestParam(name = "name", required = false, defaultValue = "me") name: String) : String {
        model.addAttribute("name", name)
        return "index"
    }

    @RequestMapping("/test")
    @ResponseBody
    fun test(): String = "test"

}
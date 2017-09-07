package com.soutvoid.tvpr.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

@Controller
class HomeController {

    @RequestMapping("/")
    fun index(model: Model, @RequestParam(name = "name", required = false, defaultValue = "Name") name: String) : ModelAndView {
        return ModelAndView("index", "name", name)
    }

    @RequestMapping("/test")
    @ResponseBody
    fun test(): String = "test"

}
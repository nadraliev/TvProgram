package com.soutvoid.tvpr

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController {

    @RequestMapping("/")
    @ResponseBody
    fun home() : String {
        return "Hellp"
    }

}
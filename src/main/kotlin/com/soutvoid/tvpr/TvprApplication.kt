package com.soutvoid.tvpr

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
class TvprApplication


fun main(args: Array<String>) {
    SpringApplication.run(TvprApplication::class.java, *args)
}

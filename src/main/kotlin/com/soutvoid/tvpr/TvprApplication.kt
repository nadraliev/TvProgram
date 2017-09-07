package com.soutvoid.tvpr

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.stereotype.Controller
import kotlin.coroutines.experimental.buildIterator

@SpringBootApplication
class TvprApplication


fun main(args: Array<String>) {
    SpringApplication.run(TvprApplication::class.java, *args)
}

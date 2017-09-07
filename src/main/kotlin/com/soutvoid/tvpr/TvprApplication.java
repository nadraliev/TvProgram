package com.soutvoid.tvpr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.soutvoid")
@SpringBootApplication
public class TvprApplication {

        public static void main(String[] args) {
            SpringApplication.run(TvprApplication.class, args);
        }

}

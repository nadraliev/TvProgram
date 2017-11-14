package com.soutvoid.tvpr;

import com.soutvoid.tvpr.service.MainService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.inject.Inject;

@ComponentScan("com.soutvoid")
@SpringBootApplication
public class TvprApplication implements CommandLineRunner {

    @Inject
    private MainService mainService;

    public static void main(String[] args) {
        SpringApplication.run(TvprApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {

    }
}

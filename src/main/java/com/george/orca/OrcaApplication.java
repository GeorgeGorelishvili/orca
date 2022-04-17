package com.george.orca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class OrcaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrcaApplication.class, args);
    }
}

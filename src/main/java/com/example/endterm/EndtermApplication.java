package com.example.endterm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EndtermApplication {

    public static void main(String[] args) {
        SpringApplication.run(EndtermApplication.class, args);
    }

}

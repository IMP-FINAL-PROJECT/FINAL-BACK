package com.imp.fluffy_mood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FluffyMoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(FluffyMoodApplication.class, args);
    }

}

package com.edl.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadStudents {
    private static final Logger log = LoggerFactory.getLogger(LoadStudents.class);

    private ApplicationContext context;


    @Bean
    CommandLineRunner run() {
        return args -> {
            log.info("List beans names:");
        };
    }

}

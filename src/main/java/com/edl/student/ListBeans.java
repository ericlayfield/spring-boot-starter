package com.edl.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.stream.Stream;

@Configuration
public class ListBeans implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(ListBeans.class);

    private ApplicationContext context;


    @Bean
    CommandLineRunner run() {
        return args -> {
            log.info("List beans names:");
            Stream<String> stream = Arrays.stream(context.getBeanDefinitionNames());
            stream.forEach(log::info);
        };
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}

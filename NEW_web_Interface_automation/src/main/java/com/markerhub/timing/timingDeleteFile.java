package com.markerhub.timing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class timingDeleteFile {
    public static void main(String[] args) {
        SpringApplication.run(timingDeleteFile.class,args);
    }
}

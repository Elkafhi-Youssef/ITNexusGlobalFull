package com.itnexusglobal.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class PersonApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PersonApplication.class, args);
    }

}

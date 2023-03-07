package com.itnexusglobal.profil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class ProfilApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ProfilApplication.class, args);
    }

}

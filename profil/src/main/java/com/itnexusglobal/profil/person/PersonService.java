package com.itnexusglobal.profil.person;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PERSON-SERVICE")
public interface PersonService {
    @GetMapping("/persons/{id}")
    Person findPersonById( @PathVariable Long id);
}

package com.itnexusglobal.profil.person;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;
    private String image;
    private String tel;
    private String linkedIn;
    private String github;


}
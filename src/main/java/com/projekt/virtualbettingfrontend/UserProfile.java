package com.projekt.virtualbettingfrontend;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserProfile {
    private String firstName;
    private String secondName;
    private String pesel;
    private LocalDate dateOfBirth;
    private String country;
    private String city;

    private String email;
    private String password;

    public UserProfile(String firstName, String secondName, String pesel, LocalDate dateOfBirth, String country, String city, String email, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.pesel = pesel;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.city = city;
        this.email = email;
        this.password = password;
    }
}
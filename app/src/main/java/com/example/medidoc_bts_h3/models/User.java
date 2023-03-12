package com.example.medidoc_bts_h3.models;

public class User {
    public String firstName;
    public String lastName;
    public String phone ;
    public Boolean isDoctor;

    public User() {}

    public User(String firstname, String lastName, String phone, Boolean isDoctor)
    {
        this.firstName = firstname;
        this.lastName = lastName;
        this.phone = phone;
        this.isDoctor = isDoctor;
    }
}

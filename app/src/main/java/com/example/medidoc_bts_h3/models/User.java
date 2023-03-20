package com.example.medidoc_bts_h3.models;

import android.provider.ContactsContract;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class User {
    public String firstName;
    public String email;
    public String lastName;
    public String phone ;
    public Boolean isDoctor;
    public String password;

    public User() {}

    public User(String firstname, String lastName, String email, String phone, Boolean isDoctor, String password)
    {
        this.firstName = firstname;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.isDoctor = isDoctor;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getDoctor() {
        return isDoctor;
    }

    public void setDoctor(Boolean doctor) {
        isDoctor = doctor;
    }
}

class Profile {
    public ArrayList<ProfileData> data;

    public ArrayList<ProfileData> itemsProfileData(String json) {
        return new Gson().fromJson(json, (Type) ProfileData.class);
    }
}

class ProfileData {
    public String last_name;
    public String first_name;
    public String email;
    public String phone;

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}


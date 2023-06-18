package com.example.medidoc_bts_h3.models;

import java.util.Date;

public class Patient {
    public Integer id;
    public String first_name;
    public String last_name;
    public String email;
    public String phone;
    public String nationality;
    public Date birthday_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBirthday_date() {
        return birthday_date;
    }

    public void setBirthday_date(Date birthday_date) {
        this.birthday_date = birthday_date;
    }
}

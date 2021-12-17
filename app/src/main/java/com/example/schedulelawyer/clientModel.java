package com.example.schedulelawyer;

public class clientModel {
    String name;
    String city;
    String email;
    public clientModel() {
    }

    public clientModel(String name, String city, String email) {
        this.name = name;
        this.city = city;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

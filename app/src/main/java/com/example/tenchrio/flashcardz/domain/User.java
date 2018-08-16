package com.example.tenchrio.flashcardz.domain;

public class User extends abstractModel {
    private String naam;
    private String token;
    private String email;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String naam, String token, String email) {
        this.naam = naam;
        this.token = token;
        this.email = email;
    }
}

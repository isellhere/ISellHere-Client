package com.example.grupoes.projetoes.models;

public class Session {
    private String token;
    private String username;
    private String password;
    private String email;

    public Session(String token, String username, String password, String email) {
        this.token = token;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

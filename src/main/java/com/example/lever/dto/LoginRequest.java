package com.example.lever.dto;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
    public LoginRequest() {
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}

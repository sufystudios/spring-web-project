package com.luv2code.springdemo.mvc;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class User {
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotBlank(message="Required")
    @NotNull(message="Required")
    private String username;
    @NotBlank(message="Required")
    @NotNull(message="Required")
    private String password;

}

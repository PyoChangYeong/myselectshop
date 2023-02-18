package com.example.myselectshop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Setter
@Getter
public class SignupRequestDto {
    private String username;

    private String password;
    private String email;
    private boolean admin = false;
    private String adminToken = "";

    public SignupRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
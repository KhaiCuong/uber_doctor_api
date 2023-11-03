package com.example.demo.requests.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignInRequest {
    @NotEmpty(message = "email is not empty")
    @Email(message = "email is a@gmail.com ....")
    private String email;

    @NotEmpty(message = "password is not empty")
    private String password;
}

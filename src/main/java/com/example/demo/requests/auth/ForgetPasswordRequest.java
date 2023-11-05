package com.example.demo.requests.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ForgetPasswordRequest {
    private String email;
    @NotEmpty(message = "password is not empty")
    private String password;
    @NotEmpty(message = "confirm password is not empty")
    private String confirmPassword;
}

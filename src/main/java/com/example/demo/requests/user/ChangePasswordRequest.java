package com.example.demo.requests.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    @NotEmpty(message = "old password is not empty")
    private String oldPassword;
    @NotEmpty(message = "password is not empty")
    private String password;
    @NotEmpty(message = "confirm password is not empty")
    private String confirmPassword;
}

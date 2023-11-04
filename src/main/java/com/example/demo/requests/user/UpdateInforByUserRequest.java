package com.example.demo.requests.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateInforByUserRequest {
    @NotEmpty(message = "fullname is not empty")
    private String fullname;

    @NotEmpty(message = "phone is not empty")
    @Pattern(regexp = "^0\\d{9}$", message = "Phone number should start with 0 and have exactly 10 digits")
    private String phone;
}

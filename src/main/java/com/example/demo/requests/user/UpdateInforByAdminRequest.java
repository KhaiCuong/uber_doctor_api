package com.example.demo.requests.user;

import com.example.demo.dtos.BaseDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateInforByAdminRequest extends BaseDTO {
    @NotEmpty(message = "fullname is not empty")
    private String fullname;

    @NotEmpty(message = "email is not empty")
    @Email(message = "email is a@gmail.com ....")
    private String email;

    @NotEmpty(message = "phone is not empty")
    @Pattern(regexp = "^0\\d{9}$", message = "Phone number should start with 0 and have exactly 10 digits")
    private String phone;
}

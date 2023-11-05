package com.example.demo.requests.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MailRequest {
    @NotEmpty(message = "email is not empty")
    @Email(message = "email is a@gmail.com ....")
    private String email;
    private String subject;
    private String message;
}

package com.example.demo.dtos;

import com.example.demo.entites.RoleEnum;
import lombok.Data;

@Data
public class UserDTO extends BaseDTO{
    private String fullname;
    private String email;
    private String phone;
    private RoleEnum role;
}

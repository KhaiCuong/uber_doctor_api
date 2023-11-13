package com.example.demo.dtos;

import com.example.demo.entites.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfor {
    private String fullname;
    private String email;
    private String phone;
    private RoleEnum role;
}

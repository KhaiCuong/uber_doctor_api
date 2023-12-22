package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PatientDTO {
    private Integer id;
    private  String fullName;
    private  String email;
    private  String phoneNumber;
}

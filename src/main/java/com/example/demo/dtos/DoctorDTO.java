package com.example.demo.dtos;

import com.example.demo.model.Department;
import jakarta.mail.Multipart;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class DoctorDTO{
    private MultipartFile image;
    private String phoneNumber;
    private String password;
    private String fullName;
    private String email;
    private String Spectiality;
    private Integer Exp;
    private Boolean Accepted;
    private Double Price;
    private String Address;
    private Boolean Status;
    private Integer Rate;
    private Double wallet;
    private String bankingAccount;
    private Department department_id;

}

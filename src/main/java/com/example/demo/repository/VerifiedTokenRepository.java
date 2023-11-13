package com.example.demo.repository;

import com.example.demo.entites.verifiedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifiedTokenRepository extends JpaRepository <verifiedToken,String>{
    public verifiedToken findByEmail(String email);

}

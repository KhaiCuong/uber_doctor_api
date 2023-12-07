package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Doctor;
import com.example.demo.model.Payment;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByPhoneNumber(String phoneNumber);
    Optional<Doctor> findById(Long id);

}
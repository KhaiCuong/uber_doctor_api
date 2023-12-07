package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByPhoneNumber(String phoneNumber);
    Optional<Patient> findById(Integer id);
}

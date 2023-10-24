package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}

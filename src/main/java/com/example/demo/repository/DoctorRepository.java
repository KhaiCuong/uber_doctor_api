package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;


import com.example.demo.model.Doctor;
import com.example.demo.model.Payment;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByPhoneNumber(String phoneNumber);
    Optional<Doctor> findById(Long id);

    @Query("SELECT d FROM Doctor d ORDER BY d.Accepted DESC")
    List<Doctor> findAllSortedByAccepted();


}
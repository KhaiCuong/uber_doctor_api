package com.example.demo.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findBySesmesterIdAndStudentId(Integer sesmesterId, Integer studentId);
    List<Patient> findByRoomTypeAndNumberRoomAndSesmesterId(String roomType, Integer numberRoom, Integer semesterId);
    List<Patient> findByStatus(Integer status);
    List<Patient> findAll(Sort sort);
}

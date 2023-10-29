package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Department;


public interface DepartmentRepository extends JpaRepository<Department, String>  {
    Optional<Department> findById(String id);
}

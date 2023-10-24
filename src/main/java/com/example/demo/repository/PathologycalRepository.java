package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Pathologycal;

public interface PathologycalRepository extends JpaRepository<Pathologycal, Integer> {
}


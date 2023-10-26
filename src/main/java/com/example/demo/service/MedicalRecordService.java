package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MedicalRecord;
import com.example.demo.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    public MedicalRecord getMedicalRecordById(Integer id) {
        return medicalRecordRepository.findById(id).orElse(null);
    }
    public void deleteMedicalRecord(Integer id) {
        medicalRecordRepository.deleteById(id);
    }
    
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
       
        return medicalRecordRepository.save(medicalRecord);
    }
}


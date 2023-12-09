package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
    
    public Patient getPatientByPhone(String phone) {
  	  Optional<Patient> optionalPatient = patientRepository.findByPhoneNumber(phone);
  	    return optionalPatient.orElse(null);
  }

    public Patient getPatientById(Integer id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Integer id, Patient patient) {
        if (patientRepository.existsById(id)) {
            patient.setId(id);
            return patientRepository.save(patient);
        }
        return null;
    }

    public boolean deletePatient(Integer id) {
        patientRepository.deleteById(id);
		return true;
    }
}


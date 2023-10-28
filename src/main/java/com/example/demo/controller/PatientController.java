package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import com.example.demo.response.CustomStatusResponse;
import com.example.demo.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    @Autowired
    private CustomStatusResponse customStatusResponse;

    @GetMapping("/patient/list")
    public ResponseEntity<CustomStatusResponse<List<Patient>>> getAllPatients() {
        try {
            List<Patient> patients = patientService.getAllPatients();
            if (patients.isEmpty()) {
                return customStatusResponse.NOTFOUND404("No patients found");
            }
            return customStatusResponse.OK200("Patients found", patients);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<CustomStatusResponse<Patient>> getPatientById(@PathVariable Integer id) {
        try {
            Patient patient = patientService.getPatientById(id);
            if (patient == null) {
                return customStatusResponse.NOTFOUND404("Patient not found");
            }
            return customStatusResponse.OK200("Patient found", patient);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @PostMapping("/patient/create")
    public ResponseEntity<CustomStatusResponse<Patient>> createPatient(@RequestBody Patient patient) {
        try {
            Patient createdPatient = patientService.createPatient(patient);
            return customStatusResponse.CREATED201("Patient created", createdPatient);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @PutMapping("/update-patient/{id}")
    public ResponseEntity<CustomStatusResponse<Patient>> updatePatient(@PathVariable Integer id, @RequestBody Patient patient) {
        try {
            Patient updatedPatient = patientService.updatePatient(id, patient);
            if (updatedPatient == null) {
                return customStatusResponse.NOTFOUND404("Patient not found");
            }
            return customStatusResponse.OK200("Patient updated", updatedPatient);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @GetMapping("/delete-patient/{id}")
    public ResponseEntity<CustomStatusResponse<?>> deletePatient(@PathVariable Integer id) {
        try {
            if (patientService.deletePatient(id)) {
                return customStatusResponse.OK200("Patient deleted");
            } else {
                return customStatusResponse.NOTFOUND404("Patient not found");
            }
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }
}



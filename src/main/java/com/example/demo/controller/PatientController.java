package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.dtos.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Doctor;
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

    @CrossOrigin
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
    @CrossOrigin
    @GetMapping("/patient/check/{phoneNum}")
    public ResponseEntity<CustomStatusResponse<Long>> checkPhoneNumber(@PathVariable String phoneNum) {
        try {
        	Patient patient = patientService.getPatientByPhone(phoneNum);
        	
            if (patient == null) {
                return customStatusResponse.NOTFOUND404("No telephone number found",-1);
            } else  {
                return customStatusResponse.OK200("Registered telephone number ", patient.getId());
            }
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }
    @CrossOrigin
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
    @CrossOrigin
    @PostMapping("/patient/create")
    public ResponseEntity<CustomStatusResponse<Patient>> createPatient(@RequestBody Patient patient) {
        try {
            Patient createdPatient = patientService.createPatient(patient);
            return customStatusResponse.CREATED201("Patient created", createdPatient);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }
    @CrossOrigin
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
    @CrossOrigin
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

    @CrossOrigin
    @PostMapping("/patient/update")
//    public ResponseEntity<CustomStatusResponse<Patient>> updatePatient(@PathVariable Integer id, @RequestParam String fullName, @RequestParam String email, @RequestParam String phoneNumber) {
    public ResponseEntity<CustomStatusResponse<Patient>> updatedPatient(@RequestBody PatientDTO updatePatient) {
        try {
            Patient existingPatient = patientService.getPatientById(updatePatient.getId());
            if(existingPatient == null){
                return customStatusResponse.NOTFOUND404("Patient not found");
            }else {
                existingPatient.setId(updatePatient.getId());
                existingPatient.setFullName(updatePatient.getFullName());
                existingPatient.setEmail(updatePatient.getEmail());
                existingPatient.setPhoneNumber(updatePatient.getPhoneNumber());
                existingPatient = patientService.updatePatient(updatePatient.getId(), existingPatient);
                return customStatusResponse.OK200("Patient updated successfully", existingPatient);
            }
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }
}



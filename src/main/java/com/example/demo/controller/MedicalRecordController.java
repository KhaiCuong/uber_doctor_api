package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MedicalRecord;
import com.example.demo.response.CustomStatusResponse;
import com.example.demo.service.MedicalRecordService;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private CustomStatusResponse customStatusResponse;

    @PostMapping
    public ResponseEntity<CustomStatusResponse<?>> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        try {
            
            MedicalRecord createdMedicalRecord = medicalRecordService.createMedicalRecord(medicalRecord);
           
            return new CustomStatusResponse<>().CREATED201("Medical Record created successfully", createdMedicalRecord);
        } catch (Exception e) {
            
            return new CustomStatusResponse<>().INTERNALSERVERERROR500("An error occurred while creating the Medical Record");
        }
    }

    @GetMapping
    public ResponseEntity<CustomStatusResponse<?>> getAllMedicalRecords() {
        try {
            
            List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();
            
            return new CustomStatusResponse<>().OK200("Medical Records retrieved successfully", medicalRecords);
        } catch (Exception e) {
           
            return new CustomStatusResponse<>().INTERNALSERVERERROR500("An error occurred while retrieving Medical Records");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomStatusResponse<?>> getMedicalRecordById(@PathVariable Integer id) {
        try {
            
            MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(id);
            if (medicalRecord != null) {
                
                return new CustomStatusResponse<>().OK200("Medical Record retrieved successfully", medicalRecord);
            } else {
                
                return new CustomStatusResponse<>().NOTFOUND404("Medical Record not found");
            }
        } catch (Exception e) {
            
            return new CustomStatusResponse<>().INTERNALSERVERERROR500("An error occurred while retrieving the Medical Record");
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CustomStatusResponse<?>> updateMedicalRecord(@PathVariable Integer id, @RequestBody MedicalRecord medicalRecord) {
        try {
           
            MedicalRecord existingMedicalRecord = medicalRecordService.getMedicalRecordById(id);
            if (existingMedicalRecord != null) {
               
                existingMedicalRecord.setSymptoms(medicalRecord.getSymptoms());
                existingMedicalRecord.setPreviousSurgeries(medicalRecord.getPreviousSurgeries());
                existingMedicalRecord.setPastIllnesses(medicalRecord.getPastIllnesses());
                existingMedicalRecord.setFamilyPathologycal(medicalRecord.isFamilyPathologycal());
                existingMedicalRecord.setVaccinationHistory(medicalRecord.getVaccinationHistory());
                existingMedicalRecord.setDailyDiet(medicalRecord.getDailyDiet());
                existingMedicalRecord.setStatus(medicalRecord.isStatus());
                existingMedicalRecord.setImage(medicalRecord.getImage());
                existingMedicalRecord.setWallet(medicalRecord.getWallet());
                existingMedicalRecord.setBankingAccount(medicalRecord.getBankingAccount());

                
                MedicalRecord updatedMedicalRecord = medicalRecordService.updateMedicalRecord(existingMedicalRecord);

           
                return new CustomStatusResponse<>().OK200("Medical Record updated successfully", updatedMedicalRecord);
            } else {
               
                return new CustomStatusResponse<>().NOTFOUND404("Medical Record not found");
            }
        } catch (Exception e) {

            return new CustomStatusResponse<>().INTERNALSERVERERROR500("An error occurred while updating the Medical Record");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CustomStatusResponse<?>> deleteMedicalRecord(@PathVariable Integer id) {
        try {
          
            MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(id);
            if (medicalRecord != null) {
               
                medicalRecordService.deleteMedicalRecord(id);
                
                return new CustomStatusResponse<>().OK200("Medical Record deleted successfully");
            } else {
                
                return new CustomStatusResponse<>().NOTFOUND404("Medical Record not found");
            }
        } catch (Exception e) {
            
            return new CustomStatusResponse<>().INTERNALSERVERERROR500("An error occurred while deleting the Medical Record");
        }
    }
    
    
}



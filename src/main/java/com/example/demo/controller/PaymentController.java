package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Patient;
import com.example.demo.model.Payment;
import com.example.demo.response.CustomStatusResponse;
import com.example.demo.service.PaymentService;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private CustomStatusResponse customStatusResponse;
    
    
    
    @GetMapping("/payment/list")
    public ResponseEntity<?> getList() {
        try {
            var data = paymentService.getAllPayments();
            if (data.stream().count() > 0) {
                return customStatusResponse.OK200("Get list success", data);

            } else {
                return customStatusResponse.NOTFOUND404("list not found");
            }
        } catch (Exception ex) {

            return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
        }
    }
    @GetMapping("/payment/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Integer id) {
        try {
            Payment payment = paymentService.getPaymentById(id);
            if (payment == null) {
            	return customStatusResponse.NOTFOUND404("list not found");
            } else {
            	return customStatusResponse.OK200("Get success", payment);
            }
        } catch (Exception ex) {
           
            return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
        }
    }
    
    @PostMapping("/payment/create")
    public ResponseEntity<Patient> createPatient(@RequestBody Payment payment) {
        try {
            Payment createdPayment = paymentService.createPayment(payment);
            if(createdPayment != null) {
            	return customStatusResponse.CREATED201("Create success",createdPayment);
            } else {
                return customStatusResponse.BADREQUEST400("Create fail");
            }
            
        } catch (Exception ex) {
           
            return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
        }
    }
    
    @PutMapping("/update-payment/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody Payment payment) {
        try {
            Payment updatePayment = paymentService.updatePayment(id,payment);
            if (updatePayment != null) {
            	return customStatusResponse.OK200("Get success", payment);
            } else {
                return customStatusResponse.NOTFOUND404("list not found");
            }
            
        } catch (Exception ex) {
           
            return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
        }
    }

    
    @GetMapping("/delete-payment/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try{
            boolean checkPayment = paymentService.deletePayment(id);
            if(checkPayment) {
                return customStatusResponse.OK200("Delete success");
            } else {
                return customStatusResponse.BADREQUEST400("Delete fail");
            }
        } catch (Exception ex) {
            return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
        }
    }

    

}

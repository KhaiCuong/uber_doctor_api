package com.example.demo.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;

@Service
public class PaymentService {
	 @Autowired
	    private PaymentRepository paymentRepository;

	    public List<Payment> getAllPayments() {
	        return paymentRepository.findAll();
	    }

	    public Payment getPaymentById(Integer id) {
	    	Optional<Payment> optionalPaymentEntity = paymentRepository.findById(id);
	    	if (optionalPaymentEntity.isPresent()) {
	        	Payment paymentEntity = optionalPaymentEntity.get();
	            return paymentEntity;
	        } return null;
	    }

	    public Payment createPayment(Payment payment) {
	        return paymentRepository.save(payment);
	    }

	    public Payment updatePayment(Integer id, Payment payment) {
	    	Optional<Payment> optionalPaymentEntity = paymentRepository.findById(id);

	        if (optionalPaymentEntity.isPresent()) {
	        	payment.setId(id);
	        	paymentRepository.save(payment);
		        return payment;

	        } else {
		        return null;
	        }
	    }

	    public boolean deletePayment(Integer id) {	    	
	    	Optional<Payment> optionalPaymentEntity = paymentRepository.findById(id);
	        if (optionalPaymentEntity.isPresent()) {
	        	Payment paymentEntity = optionalPaymentEntity.get();
	        	paymentRepository.delete(paymentEntity);
	            return true;
	        } else {
	        	return false;
	        }
	    }
}

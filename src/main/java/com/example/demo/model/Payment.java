package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotEmpty
	@Column(name = "payment_phone")
	private String paymentPhone;
	
	@NotEmpty
	@Column(name = "status")
	private Boolean Status;
	
	@NotEmpty
	@Column(name = "price")
	private Double Price;
	
	@NotEmpty
	@Column(name = "patient_name")
	private String patientName;
	
	@OneToOne()
	private Booking booking;
	
}

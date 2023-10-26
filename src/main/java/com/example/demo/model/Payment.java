package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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
	
	@Column(name = "status")
	private Boolean Status = true;
	
	@NotNull
    @DecimalMin(value = "0.0", inclusive = false)
	@Column(name = "price")
	private Double Price;
	
	@NotEmpty
	@Column(name = "patient_name")
	private String patientName;
	
	@OneToOne
	@JoinColumn(name = "id")
	private Booking booking;

	public Payment(Integer id, @NotEmpty String paymentPhone, @NotEmpty Boolean status, @NotEmpty Double price,
			@NotEmpty String patientName, Booking booking) {
		super();
		this.id = id;
		this.paymentPhone = paymentPhone;
		Status = status;
		Price = price;
		this.patientName = patientName;
		this.booking = booking;
	}

	public Payment() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPaymentPhone() {
		return paymentPhone;
	}

	public void setPaymentPhone(String paymentPhone) {
		this.paymentPhone = paymentPhone;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	
	
	
}

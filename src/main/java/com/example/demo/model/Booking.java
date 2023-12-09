package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotEmpty
	@Column(name = "status_booking")
	private String statusBooking;

	@Column(name = "is_available")
	private Boolean isAvailable;

	
	@Column(name = "booking_date")
	private Date bookingDate;


	@Column(name = "appointment_date")
	private LocalDate appointmentDate;


	@Column(name = "appointment_time")
	private LocalTime appointmentTime;

	@Column(name = "symptoms")
	private String symptoms;

	@Column(name = "notes")
	private String notes;


	@Column(name = "price")
	private Double price;

	@Column(name = "booking_attached_file")
	private String bookingAttachedFile;

	@JsonIgnoreProperties("bookings")
	@ManyToOne()
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	
	private Patient patients;

	@JsonIgnoreProperties("bookings")
	@ManyToOne()
	@JoinColumn(name = "doctor_id", referencedColumnName = "id")
	private Doctor doctors;



	// @ManyToOne()
	// @JoinColumn(name = "review_id", referencedColumnName = "id")
	// private Record records;

//	@OneToOne(mappedBy = "bookings")
//	private Payment payment;

}

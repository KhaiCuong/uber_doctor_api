package com.example.demo.model;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotEmpty
	@Column(name = "completed")
	private Boolean Completed;
	
	@NotEmpty
	@Column(name = "time")
	private Date Time;
	
	@NotEmpty
	@Column(name = "place")
	private String Place;
	
	@NotEmpty
	@Column(name = "price")
	private Double Price;
	
	@OneToOne(mappedBy = "booking")
    private Payment payment;
}

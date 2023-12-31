package com.example.demo.model;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="patients")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
//	@NotEmpty
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name="password")
	private String password;
	
	@NotEmpty
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="role")
	private Boolean role = false;
	
	@Email(message = "Email should be email format")
	@Column(name = "email", length = 255, nullable = true)
	private String email;
	
	@Column(name="address")
	private String Address;
	
	@Column(name="status")
	private Boolean Status = true;
	
	
	@Column(name="rate")
	private Integer Rate = 5;
	
	@Column(name="wallet")
	private Double wallet = 0.0;
	
	@Column(name="banking_account")
	private String bankingAccount;
	
	@OneToOne(mappedBy = "BNpatient" )
	@JsonIgnore
	private MedicalRecord medicalRecord;



	@OneToMany(mappedBy = "patients", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<Booking> bookings;
	
	public Patient(MedicalRecord medicalRecord) {
		super();
		this.medicalRecord = medicalRecord;
	}

public Patient() {
	// TODO Auto-generated constructor stub
}

public Patient(Integer id, @NotEmpty String phoneNumber, String password, @NotEmpty String fullName, Boolean role,
		@Email(message = "Email should be email format") String email, String address, Boolean status, Integer rate,
		Double wallet, String bankingAccount, MedicalRecord medicalRecord, List<Booking> bookings) {
	super();
	this.id = id;
	this.phoneNumber = phoneNumber;
	this.password = password;
	this.fullName = fullName;
	this.role = role;
	this.email = email;
	Address = address;
	Status = status;
	Rate = rate;
	this.wallet = wallet;
	this.bankingAccount = bankingAccount;
	this.medicalRecord = medicalRecord;
	this.bookings = bookings;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getFullName() {
	return fullName;
}

public void setFullName(String fullName) {
	this.fullName = fullName;
}

public Boolean getRole() {
	return role;
}

public void setRole(Boolean role) {
	this.role = role;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getAddress() {
	return Address;
}

public void setAddress(String address) {
	Address = address;
}

public Boolean getStatus() {
	return Status;
}

public void setStatus(Boolean status) {
	Status = status;
}

public Integer getRate() {
	return Rate;
}

public void setRate(Integer rate) {
	Rate = rate;
}

public Double getWallet() {
	return wallet;
}

public void setWallet(Double wallet) {
	this.wallet = wallet;
}

public String getBankingAccount() {
	return bankingAccount;
}

public void setBankingAccount(String bankingAccount) {
	this.bankingAccount = bankingAccount;
}

public MedicalRecord getMedicalRecord() {
	return medicalRecord;
}

public void setMedicalRecord(MedicalRecord medicalRecord) {
	this.medicalRecord = medicalRecord;
}

public List<Booking> getBookings() {
	return bookings;
}

public void setBookings(List<Booking> bookings) {
	this.bookings = bookings;
}

}

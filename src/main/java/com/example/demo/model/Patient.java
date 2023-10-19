package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@NotEmpty
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@NotEmpty
	@Column(name="password")
	private String password;
	
	@NotEmpty
	@Column(name="full_name")
	private String fullName;
	
	@NotNull
	@Column(name="role")
	private Boolean role;
	
	@NotEmpty
	@Email(message = "Email should be email format")
	@Column(name = "email", length = 255, nullable = false)
	private String email;
	
	@NotEmpty
	@Column(name="address")
	private String Address;
	
	@NotNull
	@Column(name="status")
	private Boolean Status;
	
	@NotNull
	@Column(name="rate")
	private Integer Rate;
	
	@NotNull
	@Column(name="wallet")
	private Double wallet;
	
	@NotEmpty(message = "Banking account must not be empty")
	@Column(name="banking_account")
	private String bankingAccount;

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

	public Patient(Integer id, @NotEmpty String phoneNumber, @NotEmpty String password, @NotEmpty String fullName,
			@NotNull Boolean role, @NotEmpty @Email(message = "Email should be email format") String email,
			String address, @NotNull Boolean status, @NotNull Integer rate, @NotNull Double wallet,
			@NotEmpty String bankingAccount) {
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
	}
	
	public Patient() {
		super();
	}
	
}

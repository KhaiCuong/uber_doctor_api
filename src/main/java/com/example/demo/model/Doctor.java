package com.example.demo.model;

import java.util.List;

// import com.example.demo.model.image.DoctorImage;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "doctors")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty
	@Column(name = "phone_number")
	private String phoneNumber;

	@NotEmpty
	@Column(name = "password")
	private String password;

	@NotEmpty
	@Column(name = "full_name")
	private String fullName;

	@NotEmpty
	@Email(message = "Email should be email format")
	@Column(name = "email", length = 255, nullable = false)
	private String email;

	@NotNull
	@Column(name = "speciality")
	private String Spectiality;

	@NotNull
	@Min(0)
	@Column(name = "exp")
	private Integer Exp;

	//@NotEmpty
	@Column(name = "accepted")
	private Boolean Accepted;

//	@NotEmpty
	@Column(name = "price")
	private Double Price;

	@Column(name = "address")
	private String Address;

//	@NotNull
	@Column(name = "status")
	private Boolean Status;

	@NotNull
	@Column(name = "rate")
	private Integer Rate;

//	@NotNull
	@Column(name = "wallet")
	private Double wallet;

	@NotEmpty
	@Column(name = "banking_account")
	private String bankingAccount;

	@NotNull
	@Column(name = "imagePath")
	private String imagePath;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@JsonBackReference
	@ManyToOne()
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department departments;

	public Doctor(Long id, String phoneNumber, String password, String fullName, String email, String spectiality, Integer exp, Boolean accepted, Double price, String address, Boolean status, Integer rate, Double wallet, String bankingAccount, String imagePath, Department departments) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		Spectiality = spectiality;
		Exp = exp;
		Accepted = accepted;
		Price = price;
		Address = address;
		Status = status;
		Rate = rate;
		this.wallet = wallet;
		this.bankingAccount = bankingAccount;
		this.imagePath = imagePath;
		this.departments = departments;
	}

	public Doctor() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSpectiality() {
		return Spectiality;
	}

	public void setSpectiality(String spectiality) {
		Spectiality = spectiality;
	}

	public Integer getExp() {
		return Exp;
	}

	public void setExp(Integer exp) {
		Exp = exp;
	}

	public Boolean getAccepted() {
		return Accepted;
	}

	public void setAccepted(Boolean accepted) {
		Accepted = accepted;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
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

	public Department getDepartments() {
		return departments;
	}

	public void setDepartments(Department departments) {
		this.departments = departments;
	}

}

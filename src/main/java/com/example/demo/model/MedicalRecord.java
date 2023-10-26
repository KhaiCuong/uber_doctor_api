package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="med_record")
public class MedicalRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotEmpty
	@Column(name = "symptoms")
	private String Symptoms;
	
	@NotEmpty
	@Column(name="previous_surgeries")
	private String previousSurgeries;
	
	@NotEmpty
	@Column(name="past_illnesses")
	private String pastIllnesses;
	
	@NotNull
	@Column(name="family_pathologycal")
	private boolean familyPathologycal;
	
	@NotEmpty
	@Column(name="vaccination_history")
	private String vaccinationHistory;
	
	@NotEmpty
	@Column(name="daily_diet")
	private String dailyDiet ;
	
	@NotNull
	@Column(name="status")
	private boolean Status;
	
	@NotEmpty
	@Column(name="image")
	private String Image;
	
	@NotNull
	@Column(name="wallet")
	private Double Wallet;
	
	@NotEmpty
	@Column(name="banking_account")
	private String bankingAccount;
	
	@OneToOne(mappedBy = "medicalRecord")
    private Patient patient;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSymptoms() {
		return Symptoms;
	}

	public void setSymptoms(String symptoms) {
		Symptoms = symptoms;
	}

	public String getPreviousSurgeries() {
		return previousSurgeries;
	}

	public void setPreviousSurgeries(String previousSurgeries) {
		this.previousSurgeries = previousSurgeries;
	}

	public String getPastIllnesses() {
		return pastIllnesses;
	}

	public void setPastIllnesses(String pastIllnesses) {
		this.pastIllnesses = pastIllnesses;
	}

	public boolean isFamilyPathologycal() {
		return familyPathologycal;
	}

	public void setFamilyPathologycal(boolean familyPathologycal) {
		this.familyPathologycal = familyPathologycal;
	}

	public String getVaccinationHistory() {
		return vaccinationHistory;
	}

	public void setVaccinationHistory(String vaccinationHistory) {
		this.vaccinationHistory = vaccinationHistory;
	}

	public String getDailyDiet() {
		return dailyDiet;
	}

	public void setDailyDiet(String dailyDiet) {
		this.dailyDiet = dailyDiet;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public Double getWallet() {
		return Wallet;
	}

	public void setWallet(Double wallet) {
		Wallet = wallet;
	}

	public String getBankingAccount() {
		return bankingAccount;
	}

	public void setBankingAccount(String bankingAccount) {
		this.bankingAccount = bankingAccount;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public MedicalRecord(Integer id, @NotEmpty String symptoms, @NotEmpty String previousSurgeries,
			@NotEmpty String pastIllnesses, @NotNull boolean familyPathologycal, @NotEmpty String vaccinationHistory,
			@NotEmpty String dailyDiet, @NotNull boolean status, @NotEmpty String image, @NotNull Double wallet,
			@NotEmpty String bankingAccount, Patient patient) {
		super();
		this.id = id;
		Symptoms = symptoms;
		this.previousSurgeries = previousSurgeries;
		this.pastIllnesses = pastIllnesses;
		this.familyPathologycal = familyPathologycal;
		this.vaccinationHistory = vaccinationHistory;
		this.dailyDiet = dailyDiet;
		Status = status;
		Image = image;
		Wallet = wallet;
		this.bankingAccount = bankingAccount;
		this.patient = patient;
	}
	
	public MedicalRecord() {
		super();
	}
	
}

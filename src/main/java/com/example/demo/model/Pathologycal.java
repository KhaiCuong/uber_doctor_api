package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name="pathologycal")
public class Pathologycal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotEmpty
	@Column(name = "symptoms")
	private String Symptoms;
	
	@NotEmpty
	@Column(name="description")
	private String Description;
	
	@NotEmpty
	@Column(name="related_diseases")
	private String relatedDiseases;
	
	@NotEmpty
	@Column(name="reason")
	private String Reason;
	
	@NotEmpty
	@Column(name="solution")
	private String Solution;
	
	@ManyToMany(mappedBy = "pathologycal", fetch = FetchType.LAZY)
    private List<Department> departments;

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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getRelatedDiseases() {
		return relatedDiseases;
	}

	public void setRelatedDiseases(String relatedDiseases) {
		this.relatedDiseases = relatedDiseases;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public String getSolution() {
		return Solution;
	}

	public void setSolution(String solution) {
		Solution = solution;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	
	public Pathologycal() {
		super();
	}

	public Pathologycal(Integer id, @NotEmpty String symptoms, @NotEmpty String description,
			@NotEmpty String relatedDiseases, @NotEmpty String reason, @NotEmpty String solution,
			List<Department> departments) {
		super();
		this.id = id;
		Symptoms = symptoms;
		Description = description;
		this.relatedDiseases = relatedDiseases;
		Reason = reason;
		Solution = solution;
		this.departments = departments;
	}
	
	
}

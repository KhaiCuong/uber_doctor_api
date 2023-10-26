package com.example.demo.model;

import java.util.List;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="departments")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotEmpty
	@Column(name = "department_name")
	private String departmentName;
	
	@NotEmpty
	@Column(name = "description")
	private String Description;
	
	@NotNull
	@Column(name = "status")
	private String Status;
	
	@NotEmpty
	@Column(name = "number_of_doctors")
	private Integer number_of_Doctors;
	
	@ManyToMany
    @JoinTable(
        name = "department_doctor",
        joinColumns = @JoinColumn(name = "department_id"),
        inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> doctors;
	
	public Department(Integer id, @NotEmpty String departmentName, @NotEmpty String description, @NotNull String status,
			@NotEmpty Integer number_of_Doctors, List<Doctor> doctors) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		Description = description;
		Status = status;
		this.number_of_Doctors = number_of_Doctors;
		this.doctors = doctors;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Integer getNumber_of_Doctors() {
		return number_of_Doctors;
	}

	public void setNumber_of_Doctors(Integer number_of_Doctors) {
		this.number_of_Doctors = number_of_Doctors;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	

}

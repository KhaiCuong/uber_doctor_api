package com.example.demo.model;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="departments")
public class Department {
	@Id
	@Column(name = "id")
	private String id;
	
	@NotEmpty
	@Column(name = "department_name")
	private String departmentName;
	
	@NotEmpty
	@Column(name = "description")
	private String Description;
	
	@Column(name = "status")
	private Boolean Status = true;

	@Column(name = "number_of_doctors")
	private Integer number_of_Doctors = 0;
	@JsonManagedReference
	@OneToMany(mappedBy = "departments", fetch = FetchType.LAZY)
    private List<Doctor> doctors;


	@ManyToMany
    @JoinTable(
        name = "department_pathologycal", 
        joinColumns = @JoinColumn(name = "department_id"), 
        inverseJoinColumns = @JoinColumn(name = "pathologycal_id") 
    )
    private List<Pathologycal> pathologycal;
	
	

	public Department() {
		super();
	}



	public Department(String id, @NotEmpty String departmentName, @NotEmpty String description, Boolean status,
			Integer number_of_Doctors, List<Doctor> doctors, List<Pathologycal> pathologycal) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		Description = description;
		Status = status;
		this.number_of_Doctors = number_of_Doctors;
		this.doctors = doctors;
		this.pathologycal = pathologycal;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
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

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
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

	public List<Pathologycal> getPathologycal() {
		return pathologycal;
	}

	public void setPathologycal(List<Pathologycal> pathologycal) {
		this.pathologycal = pathologycal;
	}
	
	

	

}

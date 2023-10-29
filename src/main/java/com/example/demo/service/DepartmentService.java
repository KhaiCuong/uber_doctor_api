package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Department;
import com.example.demo.model.Payment;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.PaymentRepository;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;
	
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

	public Department getDepartmentById(String id) {
		Optional<Department> optionalDepartmentEntity = departmentRepository.findById(id);
		if (optionalDepartmentEntity.isPresent()) {
			Department departmentEntity = optionalDepartmentEntity.get();
			return departmentEntity;
		}
		return null;
	}

	public Department createDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public Department updateDepartment(String id, Department department) {
		Optional<Department> optionalDepartmentEntity = departmentRepository.findById(id);

		if (optionalDepartmentEntity.isPresent()) {
			department.setId(id);
			departmentRepository.save(department);
			return department;

		} else {
			return null;
		}
	}

	public boolean deleteDepartment(String id) {
		Optional<Department> optionalDepartmentEntity = departmentRepository.findById(id);
		if (optionalDepartmentEntity.isPresent()) {
			Department departmentEntity = optionalDepartmentEntity.get();
			departmentRepository.delete(departmentEntity);
			return true;
		} else {
			return false;
		}
	}
}

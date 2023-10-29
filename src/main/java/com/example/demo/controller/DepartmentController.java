package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Department;
import com.example.demo.response.CustomStatusResponse;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private CustomStatusResponse customStatusResponse;

	@CrossOrigin
	@GetMapping("/department/list")
	public ResponseEntity<?> getList() {
		try {
			var data = departmentService.getAllDepartments();
			if (data.stream().count() > 0) {
				return customStatusResponse.OK200("Get list success", data);

			} else {
				return customStatusResponse.NOTFOUND404("list not found");
			}
		} catch (Exception ex) {

			return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
		}
	}

	@CrossOrigin
	@GetMapping("/department/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable String id) {
		try {
			Department department = departmentService.getDepartmentById(id);
			if (department == null) {
				return customStatusResponse.NOTFOUND404("list not found");
			} else {
				return customStatusResponse.OK200("Get success", department);
			}
		} catch (Exception ex) {

			return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
		}
	}

	@CrossOrigin
	@PostMapping("/department/create")
	public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
		try {
			Department createdDepartment = departmentService.createDepartment(department);
			if (createdDepartment != null) {
				return customStatusResponse.CREATED201("Create success", createdDepartment);
			} else {
				return customStatusResponse.BADREQUEST400("Create fail");
			}

		} catch (Exception ex) {

			return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
		}
	}

	@CrossOrigin
	@PutMapping("/update-department/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable String id, @RequestBody Department department) {
		try {
			Department updateDepartment = departmentService.updateDepartment(id, department);
			if (updateDepartment != null) {
				return customStatusResponse.OK200("Get success", department);
			} else {
				return customStatusResponse.NOTFOUND404("list not found");
			}

		} catch (Exception ex) {

			return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
		}
	}

	@CrossOrigin
	@GetMapping("/delete-department/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			boolean checkDepartment = departmentService.deleteDepartment(id);
			if (checkDepartment) {
				return customStatusResponse.OK200("Delete success");
			} else {
				return customStatusResponse.BADREQUEST400("Delete fail");
			}
		} catch (Exception ex) {
			return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
		}
	}

}

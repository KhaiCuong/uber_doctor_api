package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.print.Doc;

import com.example.demo.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dtos.DoctorDTO;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.requests.auth.SignInRequest;
import com.example.demo.response.CustomStatusResponse;
import com.example.demo.service.DoctorService;
// import com.example.demo.service.image.DoctorImageService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private CustomStatusResponse customStatusResponse;

    private String uploadDirectory = "src/main/resources/static/uploads/";

    // Get List Doctors
    @CrossOrigin
    @GetMapping("/doctor/list")
    public ResponseEntity<CustomStatusResponse<List<Doctor>>> getAllDoctors() {
        try {
            List<Doctor> doctors = doctorService.getAllDoctors();
            if (doctors.isEmpty()) {
                return customStatusResponse.NOTFOUND404("No Doctor found");

            }
            return customStatusResponse.OK200("Get List of Doctor Successfully", doctors);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }
    
    @CrossOrigin
    @GetMapping("/doctor/check/{phoneNum}")
    public ResponseEntity<CustomStatusResponse<Long>> checkPhoneNumber(@PathVariable String phoneNum) {
        try {
        	Doctor doctors = doctorService.getDoctorByPhone(phoneNum);
            if (doctors == null) {
                return customStatusResponse.NOTFOUND404("No telephone number found",-1);
            } else  {
                return customStatusResponse.OK200("Registered telephone number ", doctors.getId());
            }
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping("/doctor/{id}")
    public ResponseEntity<CustomStatusResponse<Doctor>> getDoctorById(@PathVariable Long id) {
        try {
            Doctor doctor = doctorService.getDoctorById(id);
            if (doctor == null) {
                return customStatusResponse.NOTFOUND404("Doctor not found");
            }
            return customStatusResponse.OK200("Doctor found", doctor);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }


	// Create Doctor
    @CrossOrigin
	@PostMapping("/doctor/create")
	public ResponseEntity<Doctor> createProduct(@ModelAttribute DoctorDTO doctorDTO) throws Exception {
        String imagePath = "";

        if (doctorDTO.getImage() != null) {
            imagePath = storeImage(doctorDTO.getImage());
        }
		Doctor doctor = new Doctor(null, doctorDTO.getPhoneNumber(),null, doctorDTO.getFullName(),
				doctorDTO.getEmail(), doctorDTO.getSpectiality(), doctorDTO.getExp(), doctorDTO.getAccepted() == null ? false : doctorDTO.getAccepted(),
				doctorDTO.getPrice(), doctorDTO.getAddress(), doctorDTO.getStatus() == null ? false : doctorDTO.getStatus(),  doctorDTO.getRate() == null ? 5 : doctorDTO.getRate(),
                doctorDTO.getWallet() == null ? 0 : doctorDTO.getWallet(), doctorDTO.getBankingAccount(), doctorDTO.getDescription(), imagePath == null ? "" : imagePath,doctorDTO.getDepartment_id() , null);
		Doctor savedDoctor = doctorService.createDoctor(doctor);
		return customStatusResponse.OK200("Doctor created successfully", doctor);
	}
    @CrossOrigin
    @PostMapping("/doctor/createjson")
    public ResponseEntity<CustomStatusResponse<Doctor>> createDoctor(@RequestBody Doctor doctor) {
        try {

            Doctor savedDoctor = doctorService.createDoctor(doctor);
            if (savedDoctor !=  null) {
                return  customStatusResponse.OK200("Doctor created successfully", savedDoctor);
            }
            return  customStatusResponse.BADREQUEST400("Doctor created FAIL", savedDoctor);

        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }
    @CrossOrigin
    @PutMapping("/doctor/update/{id}")
    public ResponseEntity<Doctor> updateProduct(@PathVariable Long id, @ModelAttribute DoctorDTO doctorDTO)
            throws Exception {
        Doctor existingDoctor = doctorService.getDoctorById(id);
        if (existingDoctor == null) {
            return customStatusResponse.NOTFOUND404("No Doctor found");
        }

        // Kiểm tra xem người dùng đã tải lên ảnh mới hay chưa
        if (doctorDTO.getImage() != null && !doctorDTO.getImage().isEmpty()) {
            // Xóa ảnh cũ nếu tồn tại
            if (existingDoctor.getImagePath() != null) {
                deleteImage(existingDoctor.getImagePath());
            }

            // Lưu trữ ảnh mới và cập nhật đường dẫn hình ảnh
            String imagePath = storeImage(doctorDTO.getImage());
            existingDoctor.setImagePath(imagePath);
        }

        // Cập nhật thông tin sản phẩm (tên và mô tả)
        existingDoctor.setPhoneNumber(doctorDTO.getPhoneNumber());
        existingDoctor.setPassword(doctorDTO.getPassword());
        existingDoctor.setFullName(doctorDTO.getFullName());
        existingDoctor.setEmail(doctorDTO.getEmail());
        existingDoctor.setSpectiality(doctorDTO.getSpectiality());
        existingDoctor.setExp(doctorDTO.getExp());
        existingDoctor.setAccepted(doctorDTO.getAccepted());
        existingDoctor.setPrice(doctorDTO.getPrice());
        existingDoctor.setAddress(doctorDTO.getAddress());
        existingDoctor.setStatus(doctorDTO.getStatus());
        existingDoctor.setRate(doctorDTO.getRate());
        existingDoctor.setWallet(doctorDTO.getWallet());
        existingDoctor.setBankingAccount(doctorDTO.getBankingAccount());
        existingDoctor.setDescription(doctorDTO.getDescription());
        if(doctorDTO.getDepartment_id() != null) {
            existingDoctor.setDepartments(doctorDTO.getDepartment_id());

        }


        Doctor updatedDoctor = doctorService.updateDoctor(id, existingDoctor);
        return customStatusResponse.OK200("Doctor updated successfully", updatedDoctor);
    }

    @CrossOrigin
    @PutMapping("/accept-doctor/{id}")
    public ResponseEntity<Doctor> acceptDoctor(@PathVariable Long id) {
        try {
            Doctor doctor = doctorService.getDoctorById(id);
            if (doctor == null) {
                return customStatusResponse.NOTFOUND404("No Doctor found");
            } else {
                doctor.setAccepted(true);
            }
            Doctor updateDoctor = doctorService.updateDoctor(id, doctor);
            if (updateDoctor != null) {
                return customStatusResponse.OK200("Get success", doctor);
            } else {
                return customStatusResponse.NOTFOUND404("list not found");
            }

        } catch (Exception ex) {

            return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
        }
    }

    @DeleteMapping("/doctor/delete/{id}")
    public ResponseEntity<Doctor> deleteProduct(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return customStatusResponse.NOTFOUND404("No Doctor found");
        }
        var deletedDoctor = doctorService.deleteDoctor(id);
        if (deletedDoctor == true) {
            if (doctor.getImagePath() != null) {
                deleteImage(doctor.getImagePath());
            }
            return customStatusResponse.OK200("Doctor deleted successfully", deletedDoctor);
        }
        return customStatusResponse.NOTFOUND404("No Doctor found");
    }

    private String storeImage(MultipartFile image) throws Exception {
        File directory = new File(uploadDirectory);
        if (!directory.exists()) {
            directory.mkdirs(); // Tạo thư mục nếu chưa tồn tại
        }
        String fileName = UUID.randomUUID().toString() + image.getOriginalFilename();
        /// Lấy dữ liệu đầu vào từ InputStream của hình ảnh và sao chép vào tệp đích
        Path destination = Path.of(uploadDirectory, fileName);
        Files.copy(image.getInputStream(), destination);
        return  "uploads/" + fileName;
    }

	private void deleteImage(String imageExists) {
		try {
			Path imageToDelete = Paths.get(imageExists);
			Files.deleteIfExists(imageToDelete);
		} catch (IOException e) {
			// Xử lý ngoại lệ nếu xóa tệp không thành công
			e.printStackTrace();
		}
	}
}

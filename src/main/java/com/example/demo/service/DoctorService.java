package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.repository.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	DoctorRepository doctorRepository;

	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAllSortedByAccepted();
	}

    public Doctor getDoctorByPhone(String phone) {
    	  Optional<Doctor> optionalDoctor = doctorRepository.findByPhoneNumber(phone);
    	    return optionalDoctor.orElse(null);
    }

	public Doctor createDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	public Doctor getDoctorById(Long id) {
		return doctorRepository.findById(id).orElse(null);
	}

	public Doctor updateDoctor(Long id, Doctor doctor) {
		if (doctorRepository.existsById(id)) {
			doctor.setId(id);
			return doctorRepository.save(doctor);
		}
		return null;
	}

	public boolean deleteDoctor(Long id) {
		Optional<Doctor> doctorOptional = doctorRepository.findById(id);
		if(doctorOptional.isPresent()) {
			Doctor doctor = doctorOptional.get();
			// delete doctor
			doctorRepository.delete(doctor);
			return true;
		}
		return false;
	}
}

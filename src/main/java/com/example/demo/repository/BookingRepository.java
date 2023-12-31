package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAll();

   
    Optional<Booking> findById(Integer id);


//    List<Booking> findByBookingDate(Date bookingDate);
//
//    List<Booking> findByBookingDateAndAppointmentTime(Date bookingDate, LocalTime appointmentTime);
//
//    List<Booking> findAppointmentDateAndAppointmentTimeBetween(LocalDate getAppointmentDate, LocalTime localTime, LocalTime localTime1);
//

//    boolean isAvailable(LocalDate appointmentDate, LocalTime appointmentTime);
}

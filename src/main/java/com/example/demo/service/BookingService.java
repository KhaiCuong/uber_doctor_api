package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;

@Service
public class BookingService {
	@Autowired
	BookingRepository bookingRepository;

	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	public Booking getBookingById(int id) {
		Optional<Booking> optionalBookingEntity = bookingRepository.findById(id);
		if (optionalBookingEntity.isPresent()) {
			Booking BookingEntity = optionalBookingEntity.get();
			return BookingEntity;
		}
		return null;
	}


	// Booking
	public Booking bookAppointment(Booking booking) throws Exception {
		Date date = new Date();
		booking.setBookingDate(date);
		booking.setStatusBooking("pending");
		booking.setIsAvailable(true);
		return bookingRepository.save(booking);
	}

//	public Booking bookAppointment(Booking booking) throws Exception {
//		List<Booking> existingBookings = bookingRepository.findAppointmentDateAndAppointmentTimeBetween(
//				booking.getAppointmentDate(),
//				booking.getAppointmentTime().minusMinutes(30),
//				booking.getAppointmentTime().plusMinutes(30)
//		);
//		if (!existingBookings.isEmpty()) {
//			throw new Exception("There is an existing booking within 30 minutes before or after this time.");
//		}
//
//		// Set the booking status to pending
//		booking.setStatusBooking("Pending");
//
//		// Set the booking availability to true
//		booking.setIsAvailable(true);
//
//		// Save the booking to the database
//		return bookingRepository.save(booking);
//	}
	// update
	public Booking updateBooking(int id, Booking booking) {
		Optional<Booking> optionalBookingEntity = bookingRepository.findById(id);

		if (optionalBookingEntity.isPresent()) {
			booking.setId(id);
			bookingRepository.save(booking);
			return booking;

		} else {
			return null;
		}
	}

	public boolean deleteBooking(int id) {
		Optional<Booking> optionalBookingEntity = bookingRepository.findById(id);
		if (optionalBookingEntity.isPresent()) {
			Booking BookingEntity = optionalBookingEntity.get();
			bookingRepository.delete(BookingEntity);
			return true;
		} else {
			return false;
		}
	}

	// //
	// // Tạo một cuộc hẹn mới
	// public BookingDTO createBooking(BookingDTO bookingDTO) {
	// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	// Date date = new Date();
	// bookingDTO.setBookingDate(date);
	// return this.bookingRepository.save(bookingDTO);
	// }

	// // Tìm kiếm tất cả các cuộc hẹn
	// public List<BookingDTO> getAllBookings() {
	// return this.bookingRepository.findAll();
	// }

	// // Tìm kiếm một cuộc hẹn theo ID
	// public BookingDTO getBookingById(Integer id) {
	// return this.bookingRepository.findById(id).orElse(null);
	// }

	// // Cập nhật một cuộc hẹn
	// public BookingDTO updateBooking(BookingDTO bookingDTO) {
	// return this.bookingRepository.save(bookingDTO);
	// }

	// // Xóa một cuộc hẹn
	// public void deleteBooking(Integer id) {
	// this.bookingRepository.deleteById(id);
	// }
}

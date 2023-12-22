//package com.example.demo.dtos;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.Date;
//
//import com.example.demo.model.Booking;
//
//import lombok.Data;
//
//@Data
//public class BookingDTO {
//	private Integer id;
//
//	private String statusBooking;
//
//	private Boolean isAvailable;
//
//	private Date bookingDate;
//
//	private LocalDate appointmentDate;
//
//	private LocalTime appointmentTime;
//
//	private String symptoms;
//
//	private String notes;
//
//	private Double price;
//
//	private String bookingAttachedFile;
//
//	private Integer patientId;
//
//	private Long doctorId;
//
//	public BookingDTO() {
//	}
//
//	public BookingDTO(Booking booking) {
//		this.id = booking.getId();
//		this.statusBooking = booking.getStatusBooking();
//		this.isAvailable = booking.getIsAvailable();
//		this.bookingDate = booking.getBookingDate();
//		this.appointmentDate = booking.getAppointmentDate();
//		this.appointmentTime = booking.getAppointmentTime();
//		this.symptoms = booking.getSymptoms();
//		this.notes = booking.getNotes();
//		this.price = booking.getPrice();
//		this.bookingAttachedFile = booking.getBookingAttachedFile();
//
//		if (booking.getPatients() != null) {
//			this.patientId = booking.getPatients().getId();
//		}
//
//		if (booking.getDoctors() != null) {
//			this.doctorId = booking.getDoctors().getId();
//		}
//	}
//}

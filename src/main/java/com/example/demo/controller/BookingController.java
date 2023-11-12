package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.BookingDTO;
import com.example.demo.model.Booking;

import com.example.demo.response.CustomStatusResponse;
import com.example.demo.service.BookingService;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	@Autowired
	private CustomStatusResponse customStatusResponse;

	@CrossOrigin
	@GetMapping("/booking/list")
	public ResponseEntity<?> getList() {
		try {
			var data = bookingService.getAllBookings();
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
	@GetMapping("/booking/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable int id) {
		try {
			Booking booking = bookingService.getBookingById(id);
			if (booking == null) {
				return customStatusResponse.NOTFOUND404("list not found");
			} else {
				return customStatusResponse.OK200("Get success", booking);
			}
		} catch (Exception ex) {

			return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
		}
	}

	@CrossOrigin
	@PostMapping("/booking/create")
	public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
		try {
			Booking createdBookin = bookingService.bookAppointment(booking);
			if (createdBookin != null) {
				return customStatusResponse.CREATED201("Create success", createdBookin);
			} else {
				return customStatusResponse.BADREQUEST400("Create fail");
			}

		} catch (Exception ex) {

			return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
		}
	}

	@CrossOrigin
	@PutMapping("/booking/update/{id}")
	public ResponseEntity<Booking> updateBooking(@PathVariable int id, @RequestBody Booking booking) {
		try {
			Booking updateBookin = bookingService.updateBooking(id, booking);
			if (updateBookin != null) {
				return customStatusResponse.OK200("Update success", booking);
			} else {
				return customStatusResponse.NOTFOUND404("list not found");
			}

		} catch (Exception ex) {

			return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
		}
	}

	@CrossOrigin
	@DeleteMapping("/booking/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		try {
			boolean checkBooking = bookingService.deleteBooking(id);
			if (checkBooking) {
				return customStatusResponse.OK200("Delete success");
			} else {
				return customStatusResponse.BADREQUEST400("Delete fail");
			}
		} catch (Exception ex) {
			return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
		}
	}
	
	//
	

    // // Tạo một cuộc hẹn mới
	// @CrossOrigin
	// @PostMapping("/booking/create")
    // public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
		
    //     return this.bookingService.createBooking(bookingDTO);
    // }

    // // Tìm kiếm tất cả các cuộc hẹn
	// @CrossOrigin
	// @GetMapping("/booking/list")
    // public List<BookingDTO> getAllBookings() {
    //     return this.bookingService.getAllBookings();
    // }

    // // Tìm kiếm một cuộc hẹn theo ID
	// @CrossOrigin
	// @GetMapping("/booking/{id}")
    // public BookingDTO getBookingById(@PathVariable Integer id) {
    //     return this.bookingService.getBookingById(id);
    // }

    // // Cập nhật một cuộc hẹn
	// @CrossOrigin
	// @PutMapping("/booking/update/{id}")
    // public BookingDTO updateBooking(@PathVariable Integer id, @RequestBody BookingDTO bookingDTO) {
    //     bookingDTO.setId(id);
    //     return this.bookingService.updateBooking(bookingDTO);
    // }

    // // Xóa một cuộc hẹn
	// @CrossOrigin
    // @DeleteMapping("/booking/delete/{id}")
    // public void deleteBooking(@PathVariable Integer id) {
    //     this.bookingService.deleteBooking(id);
    // }
}

package com.example.demo.model;

public enum BookingStatus {
	 PENDING,
	    SCHEDULED,
	    COMPLETED,
	    CANCELED;

	    public boolean isPending() {
	        return this == PENDING;
	    }
}

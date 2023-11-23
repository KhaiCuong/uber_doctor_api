package com.example.demo.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Feedback_id")
    private Integer id;

    @NotEmpty
    @Column(name = "Rating")
    private int rating;


    @Column(name="Content")
    private String content;

    @NotEmpty
    @Column(name="isPatient")
    private boolean isPatient;

    @NotEmpty
    @Column(name="Patient_id")
    private int patient_id;

    @NotEmpty
    @Column(name="Doctor_id")
    private int doctor_id;

    @NotEmpty
    @Column(name="createdTime")
    private Date createdTime;


    @ManyToOne()
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;
}

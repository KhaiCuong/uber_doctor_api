package com.example.demo.model;

import com.example.demo.entites.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Timer;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "payment")
@SQLDelete(sql = "UPDATE payment SET deleted_at = CURRENT_TIMESTAMP, modified_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Payment extends BaseEntity {


    @NotEmpty
    @Column(name = "payment_phone")
    private String paymentPhone;

    @Column(name = "status")
    private Boolean Status = true;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "price")
    private Double Price;

    @NotEmpty
    @Column(name = "patient_name")
    private String patientName;

    private String message;
    private String URL;


    @OneToOne
    @JoinColumn(name = "id")
    private Booking booking;


}

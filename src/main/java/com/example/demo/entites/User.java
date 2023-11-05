package com.example.demo.entites;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "admin")
@SQLDelete(sql = "UPDATE user SET deleted_at = CURRENT_TIMESTAMP, modified_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at is null")

public class User extends BaseEntity {
    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}

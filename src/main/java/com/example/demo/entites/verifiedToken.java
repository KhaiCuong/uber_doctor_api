package com.example.demo.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Calendar;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "verified_token")
public class verifiedToken {
    @Id
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "token")
    private String token;

    @Column(name = "expired_at")
    private Date expiredAt;

    public verifiedToken(String email) {
        super();
        this.email = email;
        this.token = this.generateVerifiedToken();
        this.expiredAt = this.getTokenExpirationTime();
    }

    public Date getTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,10);
        return new Date(calendar.getTime().getTime());
    }

    public String generateVerifiedToken() {
        return RandomStringUtils.randomAlphanumeric(50);
    }
}

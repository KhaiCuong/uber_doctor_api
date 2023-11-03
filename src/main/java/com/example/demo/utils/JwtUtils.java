package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.dtos.UserInfor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    public static final String SECRET_KEY = "UberDoctor";

    public String generateToken(UserInfor userInfor){
        Map<String,Object> claims= new HashMap<>();
        claims.put("fullname", userInfor.getFullname());
        claims.put("email", userInfor.getEmail());
        claims.put("phone", userInfor.getPhone());
        claims.put("role", userInfor.getRole().toString());
        return createToken(claims,userInfor.getEmail());
    }

    private String createToken(Map<String, Object> claims, String email) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .withClaim("UserInfor",claims)
                .sign(algorithm);
    }
}

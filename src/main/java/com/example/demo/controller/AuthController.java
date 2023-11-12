package com.example.demo.controller;

import com.example.demo.entites.RoleEnum;
import com.example.demo.entites.User;
import com.example.demo.requests.auth.ForgetPasswordRequest;
import com.example.demo.requests.auth.MailRequest;
import com.example.demo.requests.auth.SignInRequest;
import com.example.demo.requests.auth.SignUpRequest;
import com.example.demo.response.CustomStatusResponse;
import com.example.demo.response.auth.SignInResponse;
import com.example.demo.service.AuthService;
import com.example.demo.utils.GetDataErrorUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private GetDataErrorUtils getDataErrorUtils;

    @Autowired
    private CustomStatusResponse customStatusResponse;

    @Autowired
    private AuthService authService;

    @PostMapping("/register/doctor")
    public ResponseEntity<?> registerDoctor(@RequestBody @Valid SignUpRequest signupRequest, BindingResult rs) {
        try {
            if (rs.hasErrors()) {
                var errors = getDataErrorUtils.DataError(rs);
                return customStatusResponse.BADREQUEST400("Provider data is incorrect", errors);
            }
            signupRequest.setRole(RoleEnum.DOCTOR);

            User user = authService.createUser(signupRequest);

            if (user == null) {
                return customStatusResponse.BADREQUEST400("Created user fail");
            }
            return customStatusResponse.CREATED201("Created user success", user);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@RequestBody @Valid SignUpRequest signupRequest, BindingResult rs) {
        try {
            if (rs.hasErrors()) {
                var errors = getDataErrorUtils.DataError(rs);
                return customStatusResponse.BADREQUEST400("Provider data is incorrect", errors);
            }
            signupRequest.setRole(RoleEnum.USER);

            User user = authService.createUser(signupRequest);

            if (user == null) {
                return customStatusResponse.BADREQUEST400("Created user fail");
            }
            return customStatusResponse.CREATED201("Created user success", user);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @PostMapping("/register/PATIENT")
    public ResponseEntity<?> registerPatient(@RequestBody @Valid SignUpRequest signupRequest, BindingResult rs) {
        try {
            if (rs.hasErrors()) {
                var errors = getDataErrorUtils.DataError(rs);
                return customStatusResponse.BADREQUEST400("Provider data is incorrect", errors);
            }
            signupRequest.setRole(RoleEnum.PATIENT);

            User user = authService.createUser(signupRequest);

            if (user == null) {
                return customStatusResponse.BADREQUEST400("Created user fail");
            }
            return customStatusResponse.CREATED201("Created user success", user);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid SignInRequest signInRequest, BindingResult rs) throws Exception {
        try {
            if (rs.hasErrors()) {
                var errors = getDataErrorUtils.DataError(rs);
                return customStatusResponse.BADREQUEST400("Provider data is incorrect", errors);
            }

            SignInResponse token = authService.login(signInRequest);
            return customStatusResponse.OK200("login success", token);
        } catch (Exception e) {
            return customStatusResponse.UNAUTHORIZED401(e.getMessage());
        }
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendMail(@RequestBody @Valid MailRequest mailRequest, BindingResult rs) {
        try {
            if (rs.hasErrors()) {
                var errors = getDataErrorUtils.DataError(rs);
                return customStatusResponse.BADREQUEST400("Provider data is incorrect", errors);
            }

            User emailExist = authService.findByEmail(mailRequest.getEmail());
            if (emailExist != null) {
                return customStatusResponse.BADREQUEST400("Email already exists, please enter another email");
            }

            authService.sendMail(mailRequest);
            return customStatusResponse.OK200("Please enter your email to confirm");
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @GetMapping("/send/verified-token/{email}&{token}")
    public ResponseEntity<String> verifiedMail(@PathVariable("email") String email, @PathVariable("token") String token) {
        try {
            boolean checkVerified = authService.verifiedMail(email, token);
            if (checkVerified) {
                return customStatusResponse.OK200("Verified mail success", email);
            } else {
                return customStatusResponse.BADREQUEST400("Token has expired");
            }
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @GetMapping("/forget-password")
    public ResponseEntity<String> sendMailForgetPass(@RequestBody @Valid MailRequest mailRequest, BindingResult rs) {
        try {
            if (rs.hasErrors()) {
                var errors = getDataErrorUtils.DataError(rs);
                return customStatusResponse.BADREQUEST400("Provider data is incorrect", errors);
            }

            User emailExist = authService.findByEmail(mailRequest.getEmail());
            if (emailExist == null) {
                return customStatusResponse.NOTFOUND404("Email does not exist, please re-enter email");
            }

            authService.sendMailForgetPass(mailRequest);
            return customStatusResponse.OK200("Please enter your email to confirm");
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @GetMapping("/forget-password/verified-token/{email}&{token}")
    public ResponseEntity<String> verifiedMailForgetPass(@PathVariable("email") String email, @PathVariable("token") String token) {
        try {
            boolean checkVerified = authService.verifiedMail(email, token);
            if(checkVerified) {
                return customStatusResponse.OK200("Verified mail success", email);
            } else {
                return customStatusResponse.BADREQUEST400("Token has expired");
            }
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @PostMapping("/forget-password/change-password")
    public ResponseEntity<String> changeForgetPass(@RequestBody @Valid ForgetPasswordRequest forgetPasswordRequest, BindingResult rs) {
        try {
            if (rs.hasErrors()) {
                var errors = getDataErrorUtils.DataError(rs);
                return customStatusResponse.BADREQUEST400("Provider data is incorrect", errors);
            }

            boolean checkChangeForgetPass = authService.changeForgetPasswrod(forgetPasswordRequest);

            if (checkChangeForgetPass) {
                return customStatusResponse.OK200("Change password success");
            } else {
                return customStatusResponse.BADREQUEST400("Change passwrod fail");
            }
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

}

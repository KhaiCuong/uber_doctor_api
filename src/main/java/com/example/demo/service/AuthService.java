package com.example.demo.service;

import com.example.demo.dtos.UserInfor;
import com.example.demo.entites.User;
import com.example.demo.entites.verifiedToken;
import com.example.demo.repository.AuthRepository;
import com.example.demo.repository.VerifiedTokenRepository;
import com.example.demo.requests.auth.ForgetPasswordRequest;
import com.example.demo.requests.auth.MailRequest;
import com.example.demo.requests.auth.SignInRequest;
import com.example.demo.requests.auth.SignUpRequest;
import com.example.demo.response.auth.SignInResponse;
import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.SendMailUtils;
import com.example.demo.utils.TransferDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class AuthService {
    @Autowired
    private SendMailUtils sendMailUtils;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private VerifiedTokenRepository verifiedTokenRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User findByEmail(String email) {
        return authRepository.findByEmail(email);
    }

    public User createUser(SignUpRequest signUpRequest) {
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        TransferDataUtils<User, SignUpRequest> transferDataUtils = new TransferDataUtils<>();
        User user = transferDataUtils.DTOToEntity(signUpRequest, new User());

        User createdUser = authRepository.save(user);
        return createdUser;
    }

    public SignInResponse login(SignInRequest signInRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String email = authentication.getName();
            User userEntity = findByEmail(email);

            UserInfor userInfor = new UserInfor();
            userInfor.setEmail(userEntity.getEmail());
            userInfor.setFullname(userEntity.getFullname());
            userInfor.setPhone(userEntity.getPhone());
            userInfor.setRole(userEntity.getRole());

            final String token = jwtUtils.generateToken(userInfor);
            return SignInResponse.builder().token(token).build();

        } catch (Exception e) {
            throw new Exception("email or password incorrect");
        }
    }

    public void sendMail(MailRequest mailRequest) {
        try {
            verifiedToken verifiedToken = new verifiedToken(mailRequest.getEmail());
            verifiedTokenRepository.save(verifiedToken);
            String token = verifiedToken.getToken();
            String link = "http://localhost:8080/auth/send/verified-token/" + mailRequest.getEmail() + "&" + token;
            String message = "<p>Link có thời hạn trong vòng 10p.</p> Click on the following link to verify your email: <a href='" + link + "'>" + link + "</a>";

            mailRequest
                    .setSubject("Verified token")
                    .setMessage(message);

            sendMailUtils.sendMail(mailRequest);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void sendMailForgetPass(MailRequest mailRequest) {
        try {
            verifiedToken verifiedToken = new verifiedToken(mailRequest.getEmail());
            verifiedTokenRepository.save(verifiedToken);
            String token = verifiedToken.getToken();
            String link = "http://localhost:8080/auth/forget-password/verified-token/"+mailRequest.getEmail()+"&"+token;
            String message = "<p>Link có thời hạn trong vòng 10p.</p> Click on the following link to verify your email: <a href='" +link+ "'>" +link+ "</a>";

            mailRequest
                    .setSubject("Verified token")
                    .setMessage(message);

            sendMailUtils.sendMail(mailRequest);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean verifiedMail(String email, String token) {
        verifiedToken emailExist = verifiedTokenRepository.findByEmail(email);
        if(emailExist != null) {
            Date currentTime = Calendar.getInstance().getTime();
            String tokenDB = emailExist.getToken();
            if(tokenDB.equals(token) && currentTime.before(emailExist.getExpiredAt())) {
                return true;
            }
            return false;
        } return false;
    }

    public boolean changeForgetPasswrod(ForgetPasswordRequest forgetPasswordRequest) {
        User userExist = findByEmail(forgetPasswordRequest.getEmail());

        if(userExist == null) {
            return false;
        } else {
            if(!forgetPasswordRequest.getPassword().equals(forgetPasswordRequest.getConfirmPassword())) {
                return false;
            }
            String newPasswordHash = passwordEncoder.encode(forgetPasswordRequest.getPassword());

            TransferDataUtils<User, ForgetPasswordRequest> transfer = new TransferDataUtils<>();
            User user = transfer.DTOToEntity(forgetPasswordRequest, userExist);
            user.setPassword(newPasswordHash);
            authRepository.save(user);
            return true;
        }
    }

}

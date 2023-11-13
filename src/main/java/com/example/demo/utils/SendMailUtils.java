package com.example.demo.utils;

import com.example.demo.requests.auth.MailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendMailUtils {
    @Value("thanhnxts2110018@fpt.edu.vn")
    private String fromMail;

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(MailRequest mailRequest) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            mimeMessageHelper.setFrom(fromMail);
            mimeMessageHelper.setTo(mailRequest.getEmail());
            mimeMessageHelper.setSubject(mailRequest.getSubject());
            mimeMessageHelper.setText(mailRequest.getMessage(), true); // Set the second parameter to 'true' to send HTML content

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.getMessage();
        }
    }
}

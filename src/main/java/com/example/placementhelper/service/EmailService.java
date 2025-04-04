/*
Email Service
Sends OTP via email using JavaMailSender.
Handles exceptions to ensure reliable email delivery.
*/

package com.example.placementhelper.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendOtpEmail(String to, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Your OTP Code");
            helper.setText("Your OTP code is: " + otp);
            mailSender.send(message);
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }

        /*public String generateOtp(String email) {
            String otp = String.valueOf(new Random().nextInt(900000) + 100000);
            otpStorage.put(email, otp);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    otpStorage.remove(email);
                }
            }, 5 * 60 * 1000);

            return otp;
        }*/
    }
}

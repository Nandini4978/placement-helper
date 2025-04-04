/*
Admin Service
Registers admin securely (password encryption).
Generates OTP for login.
Verifies OTP before allowing access.
*/

package com.example.placementhelper.service;

import com.example.placementhelper.entity.Admin;
import com.example.placementhelper.repository.AdminRepository;
import com.example.placementhelper.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final Map<String, String> otpStorage = new HashMap<>();
    private final JwtUtil jwtUtil;

    public String login(String email, String password) {
        Optional<Admin> admin = adminRepository.findByEmail(email);

        if (admin.isPresent() && passwordEncoder.matches(password, admin.get().getPassword())) {
            return jwtUtil.generateToken(email, "ADMIN");
        }
        throw new RuntimeException("Invalid credentials");
    }

    public Admin registerAdmin(Admin admin) {
        if (adminRepository.findByEmail(admin.getEmail()).isPresent()) {
            throw new IllegalStateException("Admin email already exists");
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    /*public String generateOtp(String email) {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isEmpty()) {
            throw new IllegalStateException("Admin not found");
        }
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

    public boolean verifyOtp(String email, String otp) {
        return otpStorage.containsKey(email) && otpStorage.get(email).equals(otp);
    }
}
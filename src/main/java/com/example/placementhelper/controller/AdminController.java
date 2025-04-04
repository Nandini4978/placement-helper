package com.example.placementhelper.controller;

import com.example.placementhelper.dto.LoginRequest;
import com.example.placementhelper.dto.OtpRequest;
import com.example.placementhelper.entity.Admin;
import com.example.placementhelper.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.registerAdmin(admin));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        String token = adminService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<Map<String, String>> verifyOtp(@RequestBody OtpRequest otpRequest) {
        boolean isVerified = adminService.verifyOtp(otpRequest.getEmail(), otpRequest.getOtp());
        return isVerified
                ? ResponseEntity.ok(Map.of("message", "OTP verified successfully"))
                : ResponseEntity.status(401).body(Map.of("error", "Invalid OTP"));
    }
}

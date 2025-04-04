package com.example.placementhelper.controller;

import com.example.placementhelper.entity.Company;
import com.example.placementhelper.dto.LoginRequest;
import com.example.placementhelper.dto.OtpRequest;
import com.example.placementhelper.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/register")
    public ResponseEntity<Company> registerCompany(@RequestBody Company company) {
        return ResponseEntity.ok(companyService.registerCompany(company));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        String token = companyService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<Map<String, String>> verifyOtp(@RequestBody OtpRequest otpRequest) {
        boolean isVerified = companyService.verifyOtp(otpRequest.getEmail(), otpRequest.getOtp());
        return isVerified ? ResponseEntity.ok(Map.of("message", "OTP verified successfully"))
                : ResponseEntity.status(401).body(Map.of("error", "Invalid OTP"));
    }
}

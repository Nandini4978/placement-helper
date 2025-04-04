package com.example.placementhelper.controller;

import com.example.placementhelper.dto.LoginRequest;
import com.example.placementhelper.dto.OtpRequest;
import com.example.placementhelper.entity.User;
import com.example.placementhelper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        String token = userService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }

    /*@PostMapping("/verify-otp")
    public ResponseEntity<Map<String, String>> verifyOtp(@RequestBody OtpRequest otpRequest) {
        boolean isVerified = userService.verifyOtp(otpRequest.getEmail(), otpRequest.getOtp());
        return isVerified ? ResponseEntity.ok(Map.of("message", "OTP verified successfully"))
                : ResponseEntity.status(401).body(Map.of("error", "Invalid OTP"));
    }*/

    @PostMapping("/approve/{id}")
    public ResponseEntity<String> approveUser(@PathVariable Long id) {
        userService.approveUser(id);
        return ResponseEntity.ok("User approved successfully");
    }

    @GetMapping("/userinfo")
    public ResponseEntity<String> getUserInfo(@RequestParam String accessToken) {
        String userEmail = userService.getUserEmail(accessToken);
        return ResponseEntity.ok("User email: " + userEmail);
    }
}

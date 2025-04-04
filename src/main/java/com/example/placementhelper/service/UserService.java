/*
User Service
Registers users securely (Encrypts passwords, sets approved = false).
Generates OTP for login.
Verifies OTP for authentication.
Approves users (Admin-only).
Placeholder for JWT authentication (for future enhancements).
*/

package com.example.placementhelper.service;

import com.example.placementhelper.entity.User;
import com.example.placementhelper.repository.UserRepository;
import com.example.placementhelper.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Map<String, String> otpStorage = new HashMap<>();
    private final JwtUtil jwtUtil;

    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return jwtUtil.generateToken(email, "Student");
        }
        throw new RuntimeException("Invalid credentials");
    }

    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()) || userRepository.existsByEnrollment(user.getEnrollment())) {
            throw new IllegalStateException("Email or Enrollment already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setApproved(false);
        return userRepository.save(user);
    }
}

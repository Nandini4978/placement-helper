package com.example.placementhelper.service;

import com.example.placementhelper.entity.Company;
import com.example.placementhelper.repository.CompanyRepository;
import com.example.placementhelper.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final Map<String, String> otpStorage = new HashMap<>();
    private final JwtUtil jwtUtil;

    public String login(String email, String password) {
        Optional<Company> company = companyRepository.findByEmail(email);

        if (company.isPresent() && passwordEncoder.matches(password, company.get().getPassword())) {
            return jwtUtil.generateToken(email, "COMPANY");
        }
        throw new RuntimeException("Invalid credentials");
    }

    public Company registerCompany(Company company) {
        if (companyRepository.findByEmail(company.getEmail()).isPresent()) {
            throw new IllegalStateException("Company email already exists");
        }
        company.setPassword(passwordEncoder.encode(company.getPassword()));
        company.setApproved(false);
        return companyRepository.save(company);
    }

    /*public String generateOtp(String email) {
        Optional<Company> company = companyRepository.findByEmail(email);
        if (company.isEmpty() || !company.get().getApproved()) {
            throw new IllegalStateException("Company not found or not approved");
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

    public void approveCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalStateException("Company not found"));
        company.setApproved(true);
        companyRepository.save(company);
    }
}
package com.example.placementhelper.dto;

public class OtpRequest {
    private String email;
    private String otp;

    // ✅ Default Constructor
    public OtpRequest() {}

    // ✅ Parameterized Constructor
    public OtpRequest(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }

    // ✅ Getters
    public String getEmail() {
        return email;
    }

    public String getOtp() {
        return otp;
    }

    // ✅ Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
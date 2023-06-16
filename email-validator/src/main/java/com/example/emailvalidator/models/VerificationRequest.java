package com.example.emailvalidator.models;

import lombok.Data;

@Data
public class VerificationRequest {
    private String email;
    private int otp;
}

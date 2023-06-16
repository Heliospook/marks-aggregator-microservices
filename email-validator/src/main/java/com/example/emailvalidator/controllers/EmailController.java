package com.example.emailvalidator.controllers;

import com.example.emailvalidator.models.EmailDetails;
import com.example.emailvalidator.models.VerificationRequest;
import com.example.emailvalidator.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verify")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @PostMapping
    public String sendOtp(@RequestBody VerificationRequest req){
        EmailDetails deets = EmailDetails.builder()
                .recipient(req.getEmail())
                .subject("OTP from Marks Aggregator")
                .msgBody(String.format("OTP : %d", req.getOtp()))
                .build();
        return emailService.sendMail(deets);
    }
}

package com.example.emailvalidator.services;

import com.example.emailvalidator.models.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;

    public String sendMail(EmailDetails details){
        try{

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(sender);
            msg.setTo(details.getRecipient());
            msg.setText(details.getMsgBody());
            msg.setSubject(details.getSubject());

            javaMailSender.send(msg);
            return "Mail sent successfully";
        }catch(Exception e){
            return e.getMessage();
        }
    }
}

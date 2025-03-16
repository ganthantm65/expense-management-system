package com.expense.ExpenseManagement.Service;

import com.expense.ExpenseManagement.Repository.OtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    JavaMailSender mailSender;


    public void sendMail(String email,String otp){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Regarding OTP for retrieving password");
        message.setText("Your OTP for creating new password is "+otp);
        mailSender.send(message);
    }
}

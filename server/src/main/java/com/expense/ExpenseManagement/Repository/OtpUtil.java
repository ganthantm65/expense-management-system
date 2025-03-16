package com.expense.ExpenseManagement.Repository;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;

@Repository
public class OtpUtil {
    private static final SecureRandom random=new SecureRandom();

    public String generateOtp(){
        int otp=1000+random.nextInt(9000);
        return String.valueOf(otp);
    }
}

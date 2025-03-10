package com.expense.ExpenseManagement.Configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.net.HttpRetryException;

@Configuration
@EnableWebSecurity
public class ExpenseConfigration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request->request.anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());

        return http.build();
    }
}

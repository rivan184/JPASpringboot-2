package com.example.demo.config;

import java.lang.reflect.Constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class AppSecurityConfig {

    
    
    // CustomUserDetailService userDetailService;
    // @Autowired
    // public AppSecurityConfig(CustomUserDetailService userDetailService){
    //     this.userDetailService = userDetailService;
    // }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests((auth) -> {
            try {
                auth
                    .antMatchers("/user/**").permitAll()
                    .antMatchers("/employee/**").permitAll()
                    .antMatchers("/role/**").hasAuthority("Employee")
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticate")
                    .permitAll()
                    .and()
                    .logout();
            } catch(Exception e) {
                throw new RuntimeException(e);
            }    
        });
        
        return http.build();
    }

    @Bean
    public PasswordEncoder PasswordHashing() {
        
        return new BCryptPasswordEncoder();
    }
}

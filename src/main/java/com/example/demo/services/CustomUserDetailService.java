package com.example.demo.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class CustomUserDetailService implements UserDetails, UserDetailsService{

    private EmployeeRepository employeeRepository;
    private String email;
    private String password;
    private GrantedAuthority authority;


    
    @Autowired
    public CustomUserDetailService(EmployeeRepository employeeRepository) {
        // Employee employee = employeeRepository.findAccount(email);
              
        this.employeeRepository = employeeRepository;
        // this.email = employee.getEmail();
        // this.password = employee.getUser().getPassword();
        // this.authority = mapRolestoAuthorities(employee.getUser().getRole());

        // this.employeeRepository = employeeRepository;
        // this.email = "muhammad.rivan@batmandiri.com";
        // this.password = "rivan";
        // this.authority = new SimpleGrantedAuthority("Employee");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee data = employeeRepository.findAccount(email);

        authority = new SimpleGrantedAuthority(data.getUser().getRole().getName());

        return new User(data.getEmail(),data.getUser().getPassword(),getAuthorities());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthority = new HashSet<>();
        grantedAuthority.add(authority);
        return grantedAuthority;
    }
    // private Collection<GrantedAuthority> mapRolestoAuthorities(Role role){

    //     return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    // }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}

package com.example.demo.services;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.AppSecurityConfig;
import com.example.demo.model.Employee;
import com.example.demo.model.Role;
import com.example.demo.repositories.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("employee tidak ada"));
    }

    @Override
    public Boolean save(Employee employee) {
        employeeRepository.save(employee);

        return employeeRepository.findById(employee.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id) {
        employeeRepository.deleteById(id);
        return !employeeRepository.findById(id).isPresent();
    }

    @Override
    public Boolean findAccount(String email, String password) {
        Employee employee = employeeRepository.findAccount(email);
    
            
        
        return passwordEncoder.matches(password, employee.getUser().getPassword());
    }

    @Override
    public Integer getIdByEmail(String email) {
        Integer id = employeeRepository.getIdByEmail(email);
        return id;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findAccount(email);
        if (employee == null) {
            throw new UsernameNotFoundException(email);
        } else {
            return employee;
        }
    }

    // @Override
    // public Employee loadUserByUsername(String email) throws UsernameNotFoundException {
    //     Employee employee = employeeRepository.findAccount(email);
    //     if (employee == null) {
    //         throw new UsernameNotFoundException(email);
    //     }
    //     return new User(
    //             employee.getEmail(),
    //             employee.getUser().getPassword(),
    //             mapRolestoAuthorities(employee.getUser().getRole())
    //     );
    // }


    // private Collection<GrantedAuthority> mapRolestoAuthorities(Role role){

    //     return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    // }

    // @Override
    // public Employee loadUserByEmail(String email, String password) throws UsernameNotFoundException{
    //     Employee employee = employeeRepository.findAccount(email);
    //     if (employee == null) {
    //         throw new UsernameNotFoundException(email);
    //     }
    //     if (passwordEncoder.matches(password, employee.getUser().getPassword())) {
    //         employee.getUser().setAuthorities(mapRolestoAuthorities(employee.getUser().getRole()));
    //     }


    //     return employee;
    // }

    
    
}

package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;


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
        Employee employee = employeeRepository.findAccount(email, password);
        
        return employee != null;
    }
    
}

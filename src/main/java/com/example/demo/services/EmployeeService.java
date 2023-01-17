package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.LoginDTO;
import com.example.demo.model.Employee;


public interface EmployeeService {
    public List<Employee> getAll();
    public Employee getById(Integer id);
    public Boolean save(Employee division);
    public Boolean delete(Integer id);
    public Boolean findAccount(String email, String password);
    public Integer getIdByEmail(String email);
}

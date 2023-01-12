package com.example.demo.services;

import java.util.List;



import com.example.demo.model.Employee;
import com.example.demo.model.User;


public interface EmployeeService {
    public List<Employee> getAll();
    public Employee getById(Integer id);
    public Boolean save(Employee employee);
    public Boolean save(Employee employee, User user);
    public Boolean delete(Integer id);
}

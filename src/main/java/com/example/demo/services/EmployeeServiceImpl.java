package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.User;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleService roleService;

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
    public Boolean save(Employee employee, User user) {
        employeeRepository.save(employee);
        user.setId(employee.getId());
        
        user.setRole(roleService.getIdByMaxLevel());
        userRepository.save(user);
        
        return employeeRepository.findById(employee.getId()).isPresent();
    }
    @Override
    public Boolean delete(Integer id) {
        employeeRepository.deleteById(id);
        return !employeeRepository.findById(id).isPresent();
    }
    
}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Employee;
import com.example.demo.model.User;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;

@Controller
public class UserManagementController {
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("register")
    public String register(Model model){
        Employee employee = new Employee();
        User user = new User();
        model.addAttribute("employee", employee);
        model.addAttribute("user", user);
        
        return "employee/form";
    }

    @GetMapping(value = "login")
    public String login(Model model){
        Employee employee = new Employee();
        User user = new User();
        model.addAttribute("employee", employee);
        model.addAttribute("user", user);
        return "employee/login";
    }

    @PostMapping("login/save")
    public String save(Employee employee,User user){
       
        Boolean result = employeeService.findAccount(employee.getEmail(), user.getPassword());
        
        
        
        
        if(result){
            return "redirect:/employee";
        } else{
            return "employee/login";
        }

    }
}

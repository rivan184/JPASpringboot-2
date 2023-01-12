package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Employee;
import com.example.demo.model.User;

@Controller
public class UserManagementController {
    

    @GetMapping("register")
    public String register(Model model){
        Employee employee = new Employee();
        User user = new User();
        model.addAttribute("employee", employee);
        model.addAttribute("user", user);
        
        return "employee/form";
    }

    @GetMapping("login")
    public String login(Model model){
        
        return "employee/form";
    }
}

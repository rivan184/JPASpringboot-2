package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.LoginDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.User;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.UserService;

@Controller
public class UserManagementController {
    
    private EmployeeService employeeService;
    
    private UserService userService;

    private AuthenticationManager authenticationManager;

    @Autowired
    public UserManagementController(EmployeeService employeeService, UserService userService,AuthenticationManager authenticationManager) {
        this.employeeService = employeeService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

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
        // Employee employee = new Employee();
        // User user = new User();

        LoginDTO loginDTO = new LoginDTO();
        // model.addAttribute("employee", employee);
        // model.addAttribute("user", user);
        model.addAttribute("loginDTO",loginDTO);
        return "employee/login";
    }

    @PostMapping("login/authenticate")
    public String authenticateLogin(LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        // Boolean result = employeeService.findAccount(loginDTO.getEmail(), loginDTO.getPassword());


        // Employee employee = new Employee();
        // employee = employeeService.loadUserByEmail(loginDTO.getEmail(),loginDTO.getPassword());
        
        // UserDTO userDTO = userService.authenticateLogin(loginDTO.getEmail(), loginDTO.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);
 
        // if(result){
        //     return "redirect:/role";
        // } else{
        //     return "redirect:/login";
        // }
        
        return "redirect:/role";
    }

    // CREATE
    // GET render page
    @GetMapping(value = {"employee/password/{id}"})
    public String changePass(@PathVariable(required = false) Integer id, Model model) {
        Employee employee = new Employee();
        User user = new User();
        employee = employeeService.getById(id);
        user = userService.getById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("user", user);
        

        return "employee/password";
    }

    @GetMapping("employee/forgotpass")
    public String forgotPass(Model model){
        Employee employee = new Employee();

        model.addAttribute("employee",employee);

        return "employee/forgotpass";
    }

    @PostMapping(value = "employee/password/save/{id}")
    public String saveUpdatedPassword(@PathVariable(required = false) Integer id, User user){
        
        Boolean result = userService.updatePassword(user.getPassword(), id);

        if (result) {
            return "redirect:/employee";
        } else {
            return "employee/password/"+id;
        }
    }

    @PostMapping("employee/forgotpass/email")
    public String email(Employee employee){

        Integer id = employeeService.getIdByEmail(employee.getEmail());

        return "redirect:/employee/password/"+id;
    }
}

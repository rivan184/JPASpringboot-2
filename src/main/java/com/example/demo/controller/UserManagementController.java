package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("login/submit")
    public String submit(Employee employee,User user){
       
        Boolean result = employeeService.findAccount(employee.getEmail(), user.getPassword());
        
        
        
        
        if(result){
            return "redirect:/employee";
        } else{
            return "employee/login";
        }

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
    public String save(@PathVariable(required = false) Integer id, Employee employee, User user){
        
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

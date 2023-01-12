package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Employee;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;


@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("employees", employeeService.getAll());
        return "employee/index";
    }

    // CREATE
    // GET render page
    @GetMapping(value = {"form/{id}"})
    public String create(@PathVariable(required = false) Integer id, Model model) {
        Employee employee = new Employee();
        User user = new User();
        employee = employeeService.getById(id);
        user = userService.getById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("user", user);
        

        return "employee/form";
    }


    @PostMapping("save")
    public String save(Employee employee, User user){
        Boolean result;

        //service bisa pk 1 bisa 2, kalo 1 di siapin usernya di savenya

        result = employeeService.save(employee,user);
        
        // user.setId(employee.getId());
        // role.setId(roleService.getIdByLevel());
        // user.setRole(role);

        
        // user.setRole(roleService.getIdByMaxLevel());
        // result2 = userService.save(user);
        
        
        if(result){
            return "redirect:/employee";
        } else{
            return "employee/form";
        }

    }    

    // DELETE
    // POST
    @PostMapping(value= {"delete/{id}"})
    public String delete(@PathVariable(required = false) Integer id) {
        
        employeeService.delete(id);

        return "redirect:/employee";
    }


}

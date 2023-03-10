package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Employee;
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



    @PostMapping("save")
    public String save(Employee employee,User user){
        Boolean result;
        Boolean result2;


        result = employeeService.save(employee);
        
        user.setId(employee.getId());
        // role.setId(roleService.getIdByLevel());
        // user.setRole(role);

        
        user.setRole(roleService.getIdByMaxLevel());
        result2 = userService.save(user);
        
        
        if(result && result2){
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

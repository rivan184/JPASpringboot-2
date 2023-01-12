package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.model.Role;
import com.example.demo.services.RoleService;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("roles", roleService.getAll());
        return "role/index";
    }

    // CREATE
    // GET
    @GetMapping(value = {"form","form/{id}"})
    public String create(@PathVariable(required = false) Integer id, Model model) {
        Role role = new Role();
        if (id!=null) {
            role = roleService.getById(id);
            model.addAttribute("role", role);
        }else{
            model.addAttribute("role", role);
        }

        return "role/form";
    }

    // POST 
    @PostMapping("save")
    public String save(Role role){
        Boolean result;
        result = roleService.save(role);
        if(result){
            return "redirect:/role";
        } else{
            return "role/form";
        }

    }

    // DELETE
    // POST
    @PostMapping(value= {"delete/{id}"})
    public String delete(@PathVariable(required = false) Integer id) {
        
        roleService.delete(id);

        return "redirect:/role";
    }
    

}

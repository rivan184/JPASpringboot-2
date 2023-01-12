package com.example.demo.services;

import java.util.List;



import com.example.demo.model.Role;


public interface RoleService {
    public List<Role> getAll();
    public Role getById(Integer id);
    public Boolean save(Role division);
    public Boolean delete(Integer id);
    public Integer getIdByLevel();
    public Role getIdByMaxLevel();
}

package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;


public interface UserService {
    public List<User> getAll();
    public User getById(Integer id);
    public Boolean save(User region);
    public Boolean delete(Integer id);
    public Boolean updatePassword(String password, Integer id);
    public UserDTO authenticateLogin(String email, String password);
}

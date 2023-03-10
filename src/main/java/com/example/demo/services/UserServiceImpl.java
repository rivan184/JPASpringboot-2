package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.AppSecurityConfig;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AppSecurityConfig appSecurityConfig;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("user tidak ditemukan"));
    }

    @Override
    public Boolean save(User user) {
       
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userRepository.findById(user.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id) {
        userRepository.deleteById(id);
        return !userRepository.findById(id).isPresent();
    }

    @Override
    public Boolean updatePassword(String password, Integer id) {
        userRepository.updatePassword(passwordEncoder.encode(password), id);
        return true;
    }

    @Override
    public UserDTO authenticateLogin(String email, String password) {

        return userRepository.authenticateLogin(email, password);
    }
    
    
}

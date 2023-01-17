package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    @Modifying
    @Query(value = "UPDATE user SET password = ?1 WHERE id = ?2",nativeQuery = true)
    void updatePassword(String password, Integer id);
    
    @Query(value = "SELECT user.id, employee.fullname as fullName, employee.email,role.name as roleName FROM employee JOIN user ON employee.id = user.id JOIN role on user.role_id = role.id WHERE employee.email = ?1 AND user.password = ?2", nativeQuery = true)
    UserDTO authenticateLogin(String email, String password);
    // @Query(value = "SELECT new com.example.package.demo.dto.UserDTO  UserDTO(e.id, e.fullname, e.email, r.name) FROM employee e JOIN user u ON e.id = u.id JOIN role r on u.role_id = r.id WHERE e.email = ?1 AND u.password = ?2")
    // UserDTO authenticateLogin(String email, String password);
}

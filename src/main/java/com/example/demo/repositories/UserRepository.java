package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    @Modifying
    @Query(value = "UPDATE user SET password = ?1 WHERE id = ?2",nativeQuery = true)
    void updatePassword(String password, Integer id);
}

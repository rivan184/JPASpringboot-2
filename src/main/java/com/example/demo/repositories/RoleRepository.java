package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    //approach lain, ngambil maxlevel trs dapetin role idnya, trs taro ke user
    @Query("SELECT MAX(level) FROM Role")
    Integer findMaxRoleLevel();

    //dapetin role, trs set ke user
    @Query("SELECT r FROM Role r WHERE r.level = (SELECT MAX(level) FROM Role)")
    Role getIdByMaxLevel();
    
    //bisa pake native sql juga dipasang = true
}
